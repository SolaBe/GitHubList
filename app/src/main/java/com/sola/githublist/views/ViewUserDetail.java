package com.sola.githublist.views;

import com.sola.githublist.models.UserDetail;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public interface ViewUserDetail {

    void setUserData(UserDetail user);

    void failResponse(String message);

}
