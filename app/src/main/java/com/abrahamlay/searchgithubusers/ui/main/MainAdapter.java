package com.abrahamlay.searchgithubusers.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrahamlay.searchgithubusers.R;
import com.abrahamlay.searchgithubusers.model.ItemsItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by abrahamlay on 17/08/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_DATA=101;
    public static final int TYPE_LOADING=102;


    private List<ItemsItem> mItemList;
    private OnItemClickListener mListener;
    private boolean isMoreDataAvailable=true;

    MainAdapter(List<ItemsItem> mItemList, OnItemClickListener mListener) {
        this.mItemList = mItemList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        if(viewType==TYPE_DATA){
            return new ResultViewHolder(inflater.inflate(R.layout.item_search_result,viewGroup,false));
        }else{
            return new LoadHolder(inflater.inflate(R.layout.item_progress_bar,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final ItemsItem item=mItemList.get(i);

        if(viewHolder instanceof ResultViewHolder){
            final ResultViewHolder resultViewHolder=(ResultViewHolder) viewHolder;
            resultViewHolder.bindData(item);
            resultViewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(resultViewHolder.itemView,item,resultViewHolder.getAdapterPosition());
                }
            });
        }else{
            ((LoadHolder)viewHolder).setProgressBar(isMoreDataAvailable);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=getItemCount()-1){
            return TYPE_LOADING;
        }else{
            return TYPE_DATA;
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    class ResultViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivUser;
        private final TextView tvUser;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUser=itemView.findViewById(R.id.iv_user);
            tvUser=itemView.findViewById(R.id.tv_user);
        }

        private void bindData(ItemsItem item){
            tvUser.setText(item.getLogin());
            Glide.with(itemView.getContext())
                    .load(item.getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(ivUser);
        }

        private void setOnClickListener(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }
    }

    class LoadHolder extends RecyclerView.ViewHolder{
        private final View pb;

        public LoadHolder(View itemView) {
            super(itemView);
            pb=itemView.findViewById(R.id.progress_bar);
        }
        public void setProgressBar(boolean isActive){
            pb.setVisibility(isActive?View.VISIBLE:View.GONE);
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(View view, Object data, int position);
    }
}
