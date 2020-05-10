package com.iheb.newsapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class MyLoader extends AsyncTaskLoader<ArrayList<Story>> {
    Utils utils;
    String topic = "";

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.e("TAG", "onStartLoading: ");
        super.onStartLoading();
    }

    public MyLoader(@NonNull Context context, String Topic) {
        super(context);
        topic = Topic;
        utils = new Utils(Topic);
        Log.e("TAG", "MyLoader: ");
    }

    @Nullable
    @Override
    public ArrayList<Story> loadInBackground() {
        Log.e("TAG", "loadInBackground: ");

        return utils.extractData(utils.RequestJSON(utils.createUrl()));

    }
}
