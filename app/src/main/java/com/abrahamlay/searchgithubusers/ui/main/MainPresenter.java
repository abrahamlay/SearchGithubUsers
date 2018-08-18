package com.abrahamlay.searchgithubusers.ui.main;

import com.abrahamlay.searchgithubusers.model.SearchResult;
import com.abrahamlay.searchgithubusers.repository.search.SearchRepository;
import com.abrahamlay.searchgithubusers.util.api.RemoteCallback;

/**
 * Created by abrahamlay on 16/08/2018.
 */

public class MainPresenter implements MainContract.MainAction {
    private MainContract.MainView view;
    private SearchRepository repository;

    public MainPresenter(MainContract.MainView view, SearchRepository repository) {
        this.view = view;
        this.repository = repository;
        this.view.setPresenter(this);
    }

    @Override
    public void findUsers(String query) {
        view.showProgressBar(true);
        repository.findUser(query, 1, view,new RemoteCallback.Load<SearchResult>() {
            @Override
            public void onDataLoaded(SearchResult data) {

                if (data.getItems().size()==0){
                    view.showProgressBar(false);
                    view.showEmpty(null);
                    return;
                }

                view.showProgressBar(false);
                view.onResultLoaded(data);
            }
        });
    }

    @Override
    public void findUsers(String query, int page) {
        repository.findUser(query, page,view, new RemoteCallback.Load<SearchResult>() {
            @Override
            public void onDataLoaded(SearchResult data) {
                view.onResultLoaded(data);
            }
        });
    }
}
