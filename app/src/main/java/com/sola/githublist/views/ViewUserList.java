package com.sola.githublist.views;


import com.sola.githublist.models.UserDetail;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public interface ViewUserList {

    void onSomethingWrong(String error);

    void userDetailLoaded(UserDetail user);
}
