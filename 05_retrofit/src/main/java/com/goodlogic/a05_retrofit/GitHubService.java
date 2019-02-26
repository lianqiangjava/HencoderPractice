package com.goodlogic.a05_retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
    @GET("users/lianqiangjava/repos")
    Call<List<Repo>> getRepos();
    @GET("users/lianqiangjava")
    Call<User> getUser();

}
