package com.example.sahibj.moviebuffs.data

import android.support.annotation.NonNull
import com.example.sahibj.moviebuffs.data.remote.PopMovieRemoteDataSource
import com.example.sahibj.moviebuffs.models.Movie

/**
 * Created by sahibjaspal on 1/15/18.
 */
class PopMoviesRepository(private val remoteDataSource: PopMovieRemoteDataSource) : PopMoviesDataSource {

    override fun getTasks(@NonNull callback: PopMoviesDataSource.LoadPopMoviesCallback) {

        remoteDataSource.getTasks(object : PopMoviesDataSource.LoadPopMoviesCallback {

            override fun onPopMoviesLoaded(popMovies: List<Movie>) {
                callback.onPopMoviesLoaded(popMovies)
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}