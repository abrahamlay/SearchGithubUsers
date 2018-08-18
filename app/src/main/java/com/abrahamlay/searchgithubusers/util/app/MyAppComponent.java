package com.abrahamlay.searchgithubusers.util.app;

import com.abrahamlay.searchgithubusers.repository.RepositoryModule;
import com.abrahamlay.searchgithubusers.util.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by abrahamlay on 15/08/2018.
 */

@Component(
        modules = {
                MyAppModule.class,
                ApiModule.class,
                RepositoryModule.class
        }
)

@Singleton
public interface MyAppComponent extends MyAppGraph {
}
