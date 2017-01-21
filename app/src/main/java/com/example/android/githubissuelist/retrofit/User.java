package com.example.android.githubissuelist.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-01-21.
 */

public class User {
    @SerializedName("login") String userName;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
