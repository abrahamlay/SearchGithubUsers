package com.abrahamlay.searchgithubusers.repository.search;

import com.abrahamlay.searchgithubusers.model.SearchResult;

import com.abrahamlay.searchgithubusers.ui.BaseView;
import com.abrahamlay.searchgithubusers.util.api.CallbackWrapper;
import com.abrahamlay.searchgithubusers.util.api.RemoteCallback;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public class SearchDataSource implements SearchRepositoryContract {

    private final SearchService service;

    public SearchDataSource(SearchService service) {
        this.service=service;
    }

    @Override
    public void findUser(String query, int page, BaseView view, final RemoteCallback.Load<SearchResult> callback) {
        CompositeDisposable compositeDisposable=new CompositeDisposable();
        Disposable disposable=service.searchUsers(query,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<SearchResult>(view) {
                    @Override
                    protected void onSuccess(SearchResult searchResult) {
                        callback.onDataLoaded(searchResult);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
