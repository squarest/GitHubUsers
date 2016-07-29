package com.example.githubusers.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.githubusers.R;
import com.example.githubusers.adapters.RecyclerViewAdapter;
import com.example.githubusers.model.User;

import java.util.List;


public class MainView extends Activity implements IMainView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
        IMainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.loading();
    }

    private void createToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("GitHub Users");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "Идет загрузка", "Пожалуйста подождите", false);
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showUsers(List<User> users) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(users);
        recyclerView.smoothScrollToPosition(recyclerView.getBaseline());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
    }

}

//    private void fillUsers(List<User> records) {
//        for (int i = 0; i < 50; i++) {
//            User user = new User();
//            user.avatarUrl = "https://fshoke.com/wp-content/uploads/2016/01/Barack-Obama-and-Vladimir-Putin-merged.jpg";
//            user.email = "email";
//            records.add(user);
//        }
//    }