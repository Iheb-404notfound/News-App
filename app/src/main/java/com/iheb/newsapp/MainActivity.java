package com.iheb.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Story>> {
    com.iheb.newsapp.Utils Utils;
    ListView list;
    storyAdapter adapter;
    String topic = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting_item) {
            startActivity(new Intent(MainActivity.this, perferencesActivity.class));
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar prog = findViewById(R.id.progress);
        prog.setVisibility(View.VISIBLE);
        list = findViewById(R.id.list);
        //TODO SharedPreferences settings = this.getSharedPreferences()
        SharedPreferences sets = PreferenceManager.getDefaultSharedPreferences(this);
        topic = sets.getString(getString(R.string.pref_key), getString(R.string.pref_default));
        Utils = new Utils(topic);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            getSupportLoaderManager().initLoader(0, null, this);
        } else {
            prog.setVisibility(View.INVISIBLE);
            TextView text = findViewById(R.id.no_news);
            text.setText(R.string.offline);
            list.setEmptyView(text);
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<Story>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyLoader(this, topic);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Story>> loader, ArrayList<Story> data) {
        ProgressBar prog = findViewById(R.id.progress);
        prog.setVisibility(View.INVISIBLE);
        list = findViewById(R.id.list);
        adapter = new storyAdapter(this, R.layout.list_item, data);
        list.setEmptyView(findViewById(R.id.no_news));
        if (adapter != null && list != null && data != null) {
            list.setAdapter(adapter);
        }
        assert list != null;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Extanded.class);
                Story story = adapter.getItem(position);
                intent.putExtra("sectionName", story.getSectionName());
                intent.putExtra("webPublicationDate", story.getWebPublicationDate());
                intent.putExtra("webTitle", story.getWebTitle());
                intent.putExtra("webUrl", story.getWebUrl());
                intent.putExtra("pillarName", story.getPillarName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Story>> loader) {

    }


}
