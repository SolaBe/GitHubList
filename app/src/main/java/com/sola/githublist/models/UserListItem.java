package com.sola.githublist.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class UserListItem {

    private String login;

    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String type;

    @SerializedName("site_admin")
    private boolean isAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
