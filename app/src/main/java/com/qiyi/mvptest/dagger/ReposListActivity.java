package com.qiyi.mvptest.dagger;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.qiyi.mvptest.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposListActivity extends BaseActivity {

    @BindView(R.id.repos_rv_list)
    RecyclerView mRvList;

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    GithubApiService githubApiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repos_list;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
    }

    private void loadData(final ListAdapter adapter) {
        showLoading(true);
        githubApiService.getRepoData(getUser()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleObserver<ArrayList<Repo>>() {
            @Override
            public void onNext(ArrayList<Repo> repos) {
                showLoading(false);
                adapter.setRepos(repos);
            }

            @Override
            public void onError(Throwable e) {
                showLoading(false);
            }
        });
    }

    private String getUser() {
        return "bird1015";
    }

    public void showLoading(boolean loading) {
        Log.i("info", loading + " Repos");
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
