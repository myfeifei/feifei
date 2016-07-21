package com.qiyi.mvptest.dagger;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public interface GithubApiService {
    @GET("users/{user}/repos")
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);
}
