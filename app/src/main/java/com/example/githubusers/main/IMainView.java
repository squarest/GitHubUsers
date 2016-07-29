package com.example.githubusers.main;

import com.example.githubusers.model.User;

import java.util.List;

/**
 * Created by Chudofom on 29.07.16.
 */
public interface IMainView {
    void showProgress();
    void hideProgress();
    void showUsers(List<User> users);
}
