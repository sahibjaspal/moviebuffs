package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.SimilarMovieResponse
import javax.inject.Inject

/**
 * Created by sahibjaspal on 1/22/18.
 */
class SimilarMoviesViewModel(app: Application) : AndroidViewModel(app) {

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository

    val similarMovies: ObservableList<Movie> = ObservableArrayList()
    val dataLoading = ObservableBoolean(false)

    fun start(movieId: Int) {
        if (similarMovies.isEmpty()) {
            dataLoading.set(true)
            popMoviesRepository.getSimilarMovies(movieId, object : PopMoviesDataSource.SimilarMovieCallback {
                override fun onSimilarMoviesLoaded(similarMovieResponse: SimilarMovieResponse) {
                    dataLoading.set(false)
                    similarMovies.clear()
                    similarMovies.addAll(similarMovieResponse.similarMovies)
                }

                override fun onDataNotAvailable() {

                }
            })
        }
    }
}