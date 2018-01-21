package com.example.sahibj.moviebuffs.data

import android.support.annotation.NonNull
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.MovieDetailsResponse

/**
 * Created by sahibjaspal on 1/15/18.
 */
interface PopMoviesDataSource {

    interface LoadPopMoviesCallback {

        fun onPopMoviesLoaded(popMovies: List<Movie>)
        fun onDataNotAvailable()
    }

    interface LoadMovieCallback {
        fun onMovieLoaded(movieDetailResponse: MovieDetailsResponse)
        fun onDataNotAvailable()
    }

    fun getMovies(@NonNull callback: LoadPopMoviesCallback)

    fun getMovie(@NonNull movieId:Int, @NonNull callback: LoadMovieCallback)
}