package com.abrahamlay.searchgithubusers.repository;

import com.abrahamlay.searchgithubusers.repository.search.SearchDataSource;
import com.abrahamlay.searchgithubusers.repository.search.SearchRepository;
import com.abrahamlay.searchgithubusers.repository.search.SearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abrahamlay on 15/08/2018.
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    SearchDataSource provideSearchDataSource(SearchService service){
        return new SearchDataSource(service);
    }

    @Provides
    @Singleton
    SearchRepository provideSearchRepository(SearchDataSource dataSource){
        return new SearchRepository(dataSource);
    }

}
