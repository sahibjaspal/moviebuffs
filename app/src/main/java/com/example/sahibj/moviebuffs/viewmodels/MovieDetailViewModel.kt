package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.models.MovieDetailsResponse
import javax.inject.Inject

/**
 * Created by sahibjaspal on 1/20/18.
 */
class MovieDetailViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        val TAG = MovieDetailViewModel::class.java.name
    }

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository

    val imageUrl :ObservableField<String> = ObservableField()

    fun start(movieId: Int) {
        popMoviesRepository.getMovie(movieId, object : PopMoviesDataSource.LoadMovieCallback {
            override fun onMovieLoaded(movieDetailResponse: MovieDetailsResponse) {
                imageUrl.set("http://image.tmdb.org/t/p/w780" + movieDetailResponse.backdropPath)
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}