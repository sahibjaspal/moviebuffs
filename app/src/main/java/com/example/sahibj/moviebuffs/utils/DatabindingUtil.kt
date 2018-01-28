package com.example.sahibj.moviebuffs.utils

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

/**
 * Created by sahibjaspal on 1/21/18.
 */

object DatabindingUtil {

    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }
}