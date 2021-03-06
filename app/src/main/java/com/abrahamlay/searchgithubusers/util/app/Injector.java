package com.abrahamlay.searchgithubusers.util.app;

import android.content.Context;

import com.abrahamlay.searchgithubusers.util.api.ApiModule;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public final class Injector {
    private Injector(){

    }

    public static MyAppGraph obtain(Context context){
        return MyApplication.get(context).getInjector();
    }

    public static MyAppGraph create(MyApplication application){
        return DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(application))
                .build();
    }
}
