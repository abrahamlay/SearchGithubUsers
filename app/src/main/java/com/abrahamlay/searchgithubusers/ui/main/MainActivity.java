   package com.abrahamlay.searchgithubusers.ui.main;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.abrahamlay.searchgithubusers.R;
import com.abrahamlay.searchgithubusers.ui.BaseActivity;

public class MainActivity extends BaseActivity {

   private Toolbar toolbar;
   private SearchView searchView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       initToolbar();
       replaceFragment(new MainFragment(),false);
   }

   private void initToolbar(){
       toolbar=findViewById(R.id.toolbar);
       searchView =findViewById(R.id.sv_main);
       setSupportActionBar(toolbar);
   }

    public SearchView getSearchView() {
        return searchView;
    }
}
