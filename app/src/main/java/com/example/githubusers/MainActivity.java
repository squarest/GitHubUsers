package com.example.githubusers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {

    private List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
        ProgressDialog progressDialog = ProgressDialog.show(this, "Идет загрузка", "Пожалуйста подождите", false);
        Observable.zip(getUsers(0),getUsers(30), this::combineLists)
                .doOnTerminate(progressDialog::dismiss)
                .subscribe(a-> setRecyclerView(),
                        throwable -> Log.d("TAG", throwable.getMessage()));
    }

    private boolean combineLists(List<User> a, List<User> b) {
        users.addAll(a);
        users.addAll(b);
        if (users.size() > 50) users.subList(50, users.size()).clear();
        Log.d("TAG", "size" + users.size());
        return true;
    }

    private Observable<List<User>> getUsers(int since) {
        return GithubService.getInstance().getUsers(since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

    }

    private void createToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("GitHub Users");
        toolbar.setTitleTextColor(Color.WHITE);
    }

//    private void fillUsers(List<User> records) {
//        for (int i = 0; i < 50; i++) {
//            User user = new User();
//            user.avatarUrl = "https://fshoke.com/wp-content/uploads/2016/01/Barack-Obama-and-Vladimir-Putin-merged.jpg";
//            user.email = "email";
//            records.add(user);
//        }
//    }
}