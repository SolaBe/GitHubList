package com.sola.githublist.utils;

import com.sola.githublist.models.UserDetail;
import com.sola.githublist.models.UserListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public interface GitHubRequests {

    @GET("/users")
    Call<List<UserListItem>> getUsers(@Query("since") int since, @Query("per_page") int perPage);

    @GET("/users/{login}")
    Call<UserDetail> getUserDetail(@Path("login") String login);


}
