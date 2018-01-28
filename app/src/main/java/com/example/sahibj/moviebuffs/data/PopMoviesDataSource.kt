package com.example.sahibj.moviebuffs.data

import android.support.annotation.NonNull
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.MovieDetailsResponse
import com.example.sahibj.moviebuffs.models.AltMovieResponse
import com.example.sahibj.moviebuffs.models.MovieCastResponse

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

    interface AltMoviesCallback {
        fun altMoviesLoaded(altMovieResponse: AltMovieResponse)
        fun onDataNotAvailable()
    }

    interface LoadMovieCastCallback {
        fun movieCastLoaded(movieCastResponse: MovieCastResponse)
        fun onDataNotAvailable()
    }

    fun getMovies(@NonNull type: String, @NonNull callback: LoadPopMoviesCallback)

    fun getMovie(@NonNull movieId: Int, @NonNull callback: LoadMovieCallback)

    fun getAltMovies(@NonNull movieId: Int, @NonNull path: String,
                     @NonNull callback: AltMoviesCallback)

    fun getMovieCast(@NonNull movieId:Int, @NonNull callback: LoadMovieCastCallback)
}