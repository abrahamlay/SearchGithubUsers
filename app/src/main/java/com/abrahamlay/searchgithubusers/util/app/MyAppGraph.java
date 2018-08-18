package com.abrahamlay.searchgithubusers.util.app;


import com.abrahamlay.searchgithubusers.ui.main.MainFragment;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public interface MyAppGraph {

    void inject(MyApplication myApplication);

    void inject(MainFragment mainFragment);
}
