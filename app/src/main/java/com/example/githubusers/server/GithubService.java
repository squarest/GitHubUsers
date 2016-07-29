package com.example.githubusers.server;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chudofom on 27.07.16.
 */
public class GithubService {
    private static Api _connection = null;
    public static synchronized Api getInstance() {
        if (_connection == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            _connection = retrofit.create(Api.class);
        }
        return _connection;
    }
}
