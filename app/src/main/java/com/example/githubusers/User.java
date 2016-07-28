package com.example.githubusers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chudofom on 27.07.16.
 */
public class User {
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("login")
    public String login;
}
