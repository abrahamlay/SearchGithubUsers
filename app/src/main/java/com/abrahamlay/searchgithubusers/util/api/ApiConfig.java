package com.abrahamlay.searchgithubusers.util.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.abrahamlay.searchgithubusers.util.app.MyApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public class ApiConfig {
    public static final Gson GSON = new GsonBuilder()
            .create();
    public static final String BASE_URL="https://api.github.com/";

    public static final int NETWORK_TIMEOUT=40;

    public static final RxJava2CallAdapterFactory rxAdapter=RxJava2CallAdapterFactory.create();

    public static final int HTTP_UNPROCESSABLE_ENTITY = 422;
}
