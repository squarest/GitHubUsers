package com.example.githubusers.adapters;

/**
 * Created by Chudofom on 28.07.16.
 */

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MyBindingAdapter{

    @BindingAdapter("avatarUrl")
    public static void setAvatarUrl(ImageView avatar, String avatarUrl) {
        Picasso.with(avatar.getContext())
                .load(avatarUrl)
                .into(avatar);

    }
}
