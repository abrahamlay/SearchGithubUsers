package com.abrahamlay.searchgithubusers.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.abrahamlay.searchgithubusers.R;

/**
 * Created by abrahamlay on 16/08/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment);

        if (addToBackStack) ft.addToBackStack(null);

        ft.commit();
    }

}
