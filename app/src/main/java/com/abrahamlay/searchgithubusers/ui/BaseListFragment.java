package com.abrahamlay.searchgithubusers.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abrahamlay.searchgithubusers.R;
import com.abrahamlay.searchgithubusers.ui.main.MainAdapter;
import com.abrahamlay.searchgithubusers.ui.main.MainPresenter;
import com.abrahamlay.searchgithubusers.ui.widget.EmptyRecyclerView;
import com.abrahamlay.searchgithubusers.ui.widget.EmptyViewHolder;
import com.abrahamlay.searchgithubusers.ui.widget.EndlessRecyclerViewOnScrollListener;
import com.abrahamlay.searchgithubusers.util.api.ApiConfig;

import java.net.HttpURLConnection;


/**
 * Created by abrahamlay on 17/08/2018.
 */

public abstract class BaseListFragment extends Fragment implements BaseView<MainPresenter>{



    protected RecyclerView.Adapter adapter;

    protected ProgressBar progressBar;
    protected int pageToLoad=1;

    protected EmptyViewHolder emptyViewHolder;
    protected EmptyRecyclerView rvList;

    protected abstract void findData();

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected void initEndlessScroll() {
        rvList.setLayoutManager(getLayoutManager());
        rvList.addOnScrollListener(
                new EndlessRecyclerViewOnScrollListener(rvList.getLayoutManager()) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        pageToLoad=page+1;
                        findData();
                    }
                });
    }

    public void initState(){
        pageToLoad=1;
        adapter=null;
        emptyViewHolder.hide();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        rvList=view.findViewById(R.id.rv_list);
        progressBar=view.findViewById(R.id.progress_bar);
        View emptyView = view.findViewById(R.id.empty_view);
        rvList.setEmptyView(emptyView);
        emptyViewHolder= new EmptyViewHolder(emptyView);
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initState();
        initEndlessScroll();
    }


    @Override
    public void showProgressBar(boolean active) {
        progressBar.setVisibility(active?View.VISIBLE:View.GONE);
    }

    @Override
    public void showEmpty(String message) {
        if(isAdded()) {
            rvList.setAsEmpty();
            emptyViewHolder.show();
            emptyViewHolder.setMessage(message == null ? getString(R.string.user_cant_be_found) : message);
        }
    }


    @Override
    public void onUnknownError(int code, String errorMessage) {
        if (isAdded()) {
            if (pageToLoad == 1) {
                rvList.setAsEmpty();
                emptyViewHolder.show();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    emptyViewHolder.setMessage(getString(R.string.error_limit_request));
                    return;
                }
                if (code == ApiConfig.HTTP_UNPROCESSABLE_ENTITY) {
                    emptyViewHolder.setMessage(getString(R.string.error_empty_query));
                    return;
                }
                emptyViewHolder.setMessage(errorMessage);
            } else {
                ((MainAdapter) adapter).setMoreDataAvailable(false);
                adapter.notifyDataSetChanged();
                if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    Toast.makeText(getContext(), R.string.error_limit_request, Toast.LENGTH_LONG).show();
                    return;
                }
                if (code == ApiConfig.HTTP_UNPROCESSABLE_ENTITY) {
                    Toast.makeText(getContext(), R.string.error_empty_query, Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onTimeout() {
        if (isAdded()) {
            if (pageToLoad == 1) {
                rvList.setAsEmpty();
                emptyViewHolder.show();
                emptyViewHolder.showOnTimeout();
            } else {
                ((MainAdapter) adapter).setMoreDataAvailable(false);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), R.string.timeout_error, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNetworkError() {
        if(isAdded()){
            if(pageToLoad==1){
                rvList.setAsEmpty();
                emptyViewHolder.show();
                emptyViewHolder.showOnNetworkError();
            }else{
                ((MainAdapter)adapter).setMoreDataAvailable(false);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),R.string.network_error,Toast.LENGTH_LONG).show();
            }
        }
    }

}
