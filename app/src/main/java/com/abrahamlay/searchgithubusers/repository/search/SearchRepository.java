package com.abrahamlay.searchgithubusers.repository.search;

import com.abrahamlay.searchgithubusers.model.SearchResult;

import com.abrahamlay.searchgithubusers.ui.BaseView;
import com.abrahamlay.searchgithubusers.util.api.RemoteCallback;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public class SearchRepository implements SearchRepositoryContract {

    private final SearchDataSource dataSource;

    public SearchRepository(SearchDataSource dataSource) {
        this.dataSource=dataSource;
    }


    @Override
    public void findUser(String query, int page, BaseView view, final RemoteCallback.Load<SearchResult> callback) {
        dataSource.findUser(query, page, view,new RemoteCallback.Load<SearchResult>() {
            @Override
            public void onDataLoaded(SearchResult data) {
                callback.onDataLoaded(data);
            }
        });
    }
}
