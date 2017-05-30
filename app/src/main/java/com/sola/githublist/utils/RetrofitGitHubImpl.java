package com.sola.githublist.utils;

import com.sola.githublist.models.UserDetail;
import com.sola.githublist.models.UserListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class RetrofitGitHubImpl {

    private final String BASE_URL = "https://api.github.com";

    private final int USERS_PER_PAGE = 50;

    private static RetrofitGitHubImpl retrofitGitHub;

    private GitHubRequests requests;


    private RetrofitGitHubImpl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requests = retrofit.create(GitHubRequests.class);
    }

    public static RetrofitGitHubImpl getInstance() {
        return retrofitGitHub == null ? new RetrofitGitHubImpl() : retrofitGitHub;
    }

    public void loadPage(int since, Callback callback) {
        Call<List<UserListItem>> call = requests.getUsers(since, USERS_PER_PAGE);
        call.enqueue(callback);
    }

    public void getUserDetail(String login, Callback callback) {
        Call<UserDetail> call = requests.getUserDetail(login);
        call.enqueue(callback);
    }

}
