package com.example.githubusers.main;

import android.util.Log;

import com.example.githubusers.server.GithubService;
import com.example.githubusers.model.User;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chudofom on 29.07.16.
 */
public class MainPresenter implements IMainPresenter {
    public IMainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loading() {
        Observable.zip(getUsers(0), getUsers(30), (a, b) -> {
            a.addAll(b);
            return a;
        })
                .map(list -> list.size() > 50 ? list.subList(0, 51) : list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mainView::showProgress)
                .doOnTerminate(mainView::hideProgress)
                .subscribe(mainView::showUsers,
                        throwable -> Log.d("TAG", throwable.getMessage()));
    }

    private Observable<List<User>> getUsers(int since) {
        return GithubService.getInstance().getUsers(since);
    }
}
