package com.sola.githublist.utils;

import com.sola.githublist.models.UserDetail;
import com.sola.githublist.presenters.ListPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class UserDetailCallback implements Callback<UserDetail> {

    private ListPresenter presenter;

    public UserDetailCallback(ListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {

        if (!response.isSuccessful()) {
            presenter.loadFailure(decodeErrorMessage(response.errorBody().byteStream()));
        } else {
            presenter.setUserData(response.body());
        }
    }

    @Override
    public void onFailure(Call<UserDetail> call, Throwable t) {
        presenter.loadFailure("Load failure! Check internet connection");
    }

    private String decodeErrorMessage(InputStream is) {
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
