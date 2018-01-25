package com.example.sahibj.moviebuffs.data

import android.support.annotation.NonNull
import com.example.sahibj.moviebuffs.data.remote.PopMovieRemoteDataSource
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.MovieDetailsResponse
import com.example.sahibj.moviebuffs.models.AltMovieResponse

/**
 * Created by sahibjaspal on 1/15/18.
 */
class PopMoviesRepository(private val remoteDataSource: PopMovieRemoteDataSource) : PopMoviesDataSource {

    override fun getMovies(@NonNull callback: PopMoviesDataSource.LoadPopMoviesCallback) {

        remoteDataSource.getMovies(object : PopMoviesDataSource.LoadPopMoviesCallback {

            override fun onPopMoviesLoaded(popMovies: List<Movie>) {
                callback.onPopMoviesLoaded(popMovies)
            }

            override fun onDataNotAvailable() {

                callback.onDataNotAvailable()
            }
        })
    }

    override fun getMovie(movieId: Int, @NonNull callback: PopMoviesDataSource.LoadMovieCallback) {
        remoteDataSource.getMovie(movieId, object : PopMoviesDataSource.LoadMovieCallback {
            override fun onMovieLoaded(movieDetailResponse: MovieDetailsResponse) {
                callback.onMovieLoaded(movieDetailResponse)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getAltMovies(movieId: Int, type: String,
                              callback: PopMoviesDataSource.AltMoviesCallback) {

        remoteDataSource.getAltMovies(movieId, type, object : PopMoviesDataSource.AltMoviesCallback {

            override fun altMoviesLoaded(altMovieResponse: AltMovieResponse) {
                callback.altMoviesLoaded(altMovieResponse)
            }

            override fun onDataNotAvailable() {

                callback.onDataNotAvailable()
            }
        })
    }
}