package com.example.githubusers;

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
