package com.example.sahibj.moviebuffs.data

import android.support.annotation.NonNull
import com.example.sahibj.moviebuffs.models.Movie

/**
 * Created by sahibjaspal on 1/15/18.
 */
interface PopMoviesDataSource {

    interface LoadPopMoviesCallback {

        fun onPopMoviesLoaded(popMovies: List<Movie>)
        fun onDataNotAvailable()
    }

    fun getTasks(@NonNull callback: LoadPopMoviesCallback)
}