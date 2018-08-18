package com.abrahamlay.searchgithubusers.repository.search;

import com.abrahamlay.searchgithubusers.model.SearchResult;
import com.abrahamlay.searchgithubusers.ui.BaseView;
import com.abrahamlay.searchgithubusers.util.api.RemoteCallback;

/**
 * Created by abrahamlay on 15/08/2018.
 */

interface SearchRepositoryContract {
    void findUser(String query, int page, BaseView view, RemoteCallback.Load<SearchResult> callback);
}
