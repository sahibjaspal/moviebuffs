package com.example.sahibj.moviebuffs.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by sahibjaspal on 1/28/18.
 */

public class DataBindingUtil {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
