package com.qiyi.mvptest.dagger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiyi.mvptest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RepoViewHolder>{

    private List<Repo> mRepos;

    public ListAdapter() {
        mRepos = new ArrayList<>();
    }

    public void setRepos(List<Repo> repos) {
        mRepos = repos;
        notifyItemInserted(mRepos.size() - 1);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bindTo(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv_repo_name)
        TextView mIvRepoName;
        @BindView(R.id.item_iv_repo_detail)
        TextView mIVRepoDetail;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(Repo repo) {
            mIvRepoName.setText(repo.name);
            mIVRepoDetail.setText(String.valueOf(repo.description + "(" + repo.language + ")"));
        }
    }
}
