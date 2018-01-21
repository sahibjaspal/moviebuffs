package com.example.sahibj.moviebuffs.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by sahibjaspal on 1/21/18.
 */

public class DatabindingUtil {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}