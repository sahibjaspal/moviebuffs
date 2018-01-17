package com.example.sahibj.moviebuffs.data.remote

import android.app.Application
import android.util.Log
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.fragments.PopularMoviesFragment
import com.example.sahibj.moviebuffs.services.MovieService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by sahibjaspal on 1/15/18.
 */
class PopMovieRemoteDataSource(app: Application) :PopMoviesDataSource {

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var movieService: MovieService

    override fun getTasks(callback: PopMoviesDataSource.LoadPopMoviesCallback) {
        movieService.getPopularMovies("popular")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ popularMovieResponse ->
                    Log.v(PopularMoviesFragment.TAG, "Retrieved popular movies")
                    callback.onPopMoviesLoaded(popularMovieResponse.movies)
                }, { t: Throwable ->
                    Log.e(PopularMoviesFragment.TAG, "Failed to retrieve popular movies", t)
                    callback.onDataNotAvailable()
                })
    }
}