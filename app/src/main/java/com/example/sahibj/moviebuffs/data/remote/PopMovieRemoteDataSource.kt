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
class PopMovieRemoteDataSource(app: Application) : PopMoviesDataSource {

    companion object {
        val TAG = PopMovieRemoteDataSource::class.java.name
    }

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var movieService: MovieService

    override fun getMovies(callback: PopMoviesDataSource.LoadPopMoviesCallback) {
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

    override fun getMovie(movieId: Int, callback: PopMoviesDataSource.LoadMovieCallback) {
        movieService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieDetailResponse ->
                    Log.v(TAG, "Retrieved movie details")
                    callback.onMovieLoaded(movieDetailResponse)
                }, { t: Throwable ->
                    Log.e(TAG, "Failed to retrieve movie details", t)
                    callback.onDataNotAvailable()
                })
    }

    override fun getAltMovies(movieId: Int, type: String,
                              callback: PopMoviesDataSource.AltMoviesCallback) {
        movieService.getAltMovies(movieId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ similarMovieResponse ->
                    Log.v(TAG, "Retrieved alt movie data for type: " + type)
                    callback.altMoviesLoaded(similarMovieResponse)
                }, { t: Throwable ->
                    Log.e(TAG, "Failed to retrieve alt movie data for type: " + type, t)
                    callback.onDataNotAvailable()
                })
    }

    override fun getMovieCast(movieId: Int, callback: PopMoviesDataSource.LoadMovieCastCallback) {
        movieService.getMovieCast(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieCastResponse ->
                    Log.v(TAG, "Retrieved movie cast data")
                    callback.movieCastLoaded(movieCastResponse)
                }, { t ->
                    Log.e(TAG, "Failed to retrieve movie cast data", t)
                    callback.onDataNotAvailable()
                })
    }
}