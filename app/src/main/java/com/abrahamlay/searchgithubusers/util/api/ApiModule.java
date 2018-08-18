package com.abrahamlay.searchgithubusers.util.api;

import com.abrahamlay.searchgithubusers.repository.search.SearchService;
import com.abrahamlay.searchgithubusers.util.app.MyApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abrahamlay on 15/08/2018.
 */
@Module
public class ApiModule {

    @Provides
    String provideBaseUrl() {
        return ApiConfig.BASE_URL;
    }

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create(ApiConfig.GSON);
    }

    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
        return ApiConfig.rxAdapter;
    }

    @Provides
    public Retrofit provideAPI(String baseURL
            , GsonConverterFactory converterFactory
            , RxJava2CallAdapterFactory rxAdapter
                               ){
        return new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(rxAdapter)
                    .build();
    }

    @Provides
    public SearchService provideSearchService(Retrofit retrofit){
        return retrofit.create(SearchService.class);
    }
}
