package com.iheb.newsapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {
    private static final String SEARCH = "http://content.guardianapis.com/search";
    private static final String API_KEY_QUERY = "api-key";
    private static final String QUERY_PARAMETRE = "q";
    private static final String API_KEY = "06126a28-dd8e-4161-921a-92b92cd705fb";
    private static final String QUERY_SHOW_TAGS = "show-tags";
    private static final String SHOW_TAGS = "contributor";
    String topic = "";

    public Utils(String topic) {
        this.topic = topic;
    }

    public URL createUrl() {
        URL url = null;
        try {
            Uri.Builder builder = Uri.parse(SEARCH).buildUpon();
            builder.appendQueryParameter(QUERY_PARAMETRE, topic);
            builder.appendQueryParameter(QUERY_SHOW_TAGS, SHOW_TAGS);
            builder.appendQueryParameter(API_KEY_QUERY, API_KEY);
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public String RequestJSON(URL url) {
        String json = "";
        HttpURLConnection connection = null;
        InputStream input = null;
        Log.e("TAG", "RequestJSON: " + "IOEXCEPTION " + url.toString());
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(10000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                input = connection.getInputStream();
                InputStream in = new BufferedInputStream(input);
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    Log.e("something", " " + line);
                    builder.append(line);
                    line = bufferedReader.readLine();
                }

                json = builder.toString();
            }
            if (input != null) {
                input.close();
            }
            connection.disconnect();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return json;
    }

    public ArrayList<Story> extractData(String json) {
        ArrayList<Story> array = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject current = results.getJSONObject(i);
                String sectionName = current.getString("sectionName");
                // the current date doesn't contain illigal chars so i split it on my own
                String[] DateAndTime = current.getString("webPublicationDate").split("T");
                DateAndTime[1] = DateAndTime[1].replace("Z", "");
                SimpleDateFormat in1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat in2 = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat out = new SimpleDateFormat("E dd / HH:mm");
                String webPublicationDate = "";
                try {
                    webPublicationDate = out.format((new Date(in1.parse(DateAndTime[0]).getTime() + in2.parse(DateAndTime[1]).getTime()))).replace("/", "at");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ArrayList<String> authors = new ArrayList<>();
                JSONArray tags = current.getJSONArray("tags");
                for (int c = 0; c < tags.length(); c++) {
                    JSONObject currentTag = tags.getJSONObject(c);
                    authors.add(currentTag.getString("webTitle"));
                }
                String webTitle = current.getString("webTitle");
                Log.e("json_parsing", "extractData: " + webTitle);
                String webUrl = current.getString("webUrl");
                String pillarName;
                try {
                    pillarName = current.getString("pillarName");
                } catch (Exception e) {
                    pillarName = "Unknown";
                }
                array.add(new Story(sectionName, webPublicationDate, webTitle, webUrl, pillarName, authors));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}
