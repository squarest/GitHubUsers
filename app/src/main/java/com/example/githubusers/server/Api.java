package com.example.githubusers.server;

import com.example.githubusers.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chudofom on 27.07.16.
 */
public interface Api {
    @GET("users")
    Observable<List<User>> getUsers(@Query("since") int since);
}
