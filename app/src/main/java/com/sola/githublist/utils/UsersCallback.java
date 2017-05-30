package com.sola.githublist.utils;

import android.util.Log;

import com.sola.githublist.models.UserListItem;
import com.sola.githublist.presenters.ListPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class UsersCallback implements Callback<List<UserListItem>> {

    private static final String TAG = "User list requset";
    private ListPresenter presenter;

    public UsersCallback(ListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResponse(Call<List<UserListItem>> call, Response<List<UserListItem>> response) {

        Log.d(TAG, "response " + response.isSuccessful());

        if (!response.isSuccessful()) {
            if (response.code() == 403) {
                InputStream is = response.errorBody().byteStream();
                presenter.loadFailure(decodeErrorMessage(is));
            }
        } else {
            presenter.loadSuccesfully(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<UserListItem>> call, Throwable t) {
        Log.d(TAG, "fail " + t.getMessage());
        presenter.loadFailure("Load failure! Check internet connection");
    }

    public String decodeErrorMessage(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        try {
            while (br.read() != -1) {
                sb.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
