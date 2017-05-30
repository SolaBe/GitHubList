package com.sola.githublist.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class UserDetail implements Serializable{

    private String login;

    private String name;

    private String company;

    private String blog;

    private String location;

    private String email;

    private String bio;

    @SerializedName("public_repos")
    private int pubRepo;

    @SerializedName("public_gists")
    private int pubGists;

    private int followers;

    private int following;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPubRepo() {
        return pubRepo;
    }

    public void setPubRepo(int pubRepo) {
        this.pubRepo = pubRepo;
    }

    public int getPubGists() {
        return pubGists;
    }

    public void setPubGists(int pubGists) {
        this.pubGists = pubGists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
