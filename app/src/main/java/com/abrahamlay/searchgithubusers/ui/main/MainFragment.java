package com.abrahamlay.searchgithubusers.ui.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import com.abrahamlay.searchgithubusers.model.ItemsItem;
import com.abrahamlay.searchgithubusers.model.SearchResult;
import com.abrahamlay.searchgithubusers.repository.search.SearchRepository;
import com.abrahamlay.searchgithubusers.ui.BaseListFragment;
import com.abrahamlay.searchgithubusers.util.app.Injector;
import com.abrahamlay.searchgithubusers.util.app.MyApplication;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseListFragment implements MainContract.MainView, MainAdapter.OnItemClickListener {
    @Inject
    SearchRepository repository;

    private MainPresenter presenter;
    private SearchView searchView;
    private List<ItemsItem> mItemList;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.obtain(getActivity()).inject(this);

        new MainPresenter(this,repository);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSearchView();
    }

    private void initSearchView(){
        if( getActivity() instanceof MainActivity && getActivity()!=null) {
            searchView =((MainActivity)getActivity()).getSearchView();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                findData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                if(mItemList!=null)mItemList.clear();
//                if(adapter!=null)adapter.notifyDataSetChanged();
                initState();
                return true;
            }
        });
    }

    @Override
    public void onResultLoaded(SearchResult data) {
        if(pageToLoad==1&& adapter==null){
            mItemList=data.getItems();
            adapter= new MainAdapter(mItemList,this);
            rvList.setAdapter(adapter);
            return;
        }

        if(data==null) {
            ((MainAdapter)adapter).setMoreDataAvailable(false);
            adapter.notifyDataSetChanged();
            return;
        }

        mItemList.addAll(data.getItems());
        adapter.notifyDataSetChanged();



    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter=presenter;
    }



    @Override
    public void onItemClicked(View view, Object data, int position) {
        ItemsItem item= (ItemsItem)data;
        Toast.makeText(getContext(),item.getLogin(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void findData() {
        if(MyApplication.get(getContext()).isInternetAvailable()) {
            if(pageToLoad==1){
                presenter.findUsers(searchView.getQuery().toString());
            }else{
                presenter.findUsers(searchView.getQuery().toString(),pageToLoad);
            }
        }else{
            onNetworkError();
        }

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

}
