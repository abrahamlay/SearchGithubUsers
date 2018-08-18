package com.abrahamlay.searchgithubusers.ui.main;

import com.abrahamlay.searchgithubusers.model.SearchResult;
import com.abrahamlay.searchgithubusers.ui.BaseView;

/**
 * Created by abrahamlay on 16/08/2018.
 */

public class MainContract {
    interface MainView extends BaseView<MainPresenter> {
        void onResultLoaded(SearchResult data);
    }

    interface MainAction {
        void findUsers(String query);
        void findUsers(String query, int page);
    }
}
