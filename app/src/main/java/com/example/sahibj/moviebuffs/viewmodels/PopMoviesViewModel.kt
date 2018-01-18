package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.liveevents.SingleLiveEvent
import com.example.sahibj.moviebuffs.models.Movie
import javax.inject.Inject

/**
 * Created by sahibj on 10/1/17.
 */
class PopMoviesViewModel(app: Application): AndroidViewModel(app) {

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository

    private val openMovieEvent = SingleLiveEvent<Int>()
    val movies: ObservableList<Movie> = ObservableArrayList()
    val dataLoading = ObservableBoolean(false)

    fun getOpenMovieEvent(): SingleLiveEvent<Int> = openMovieEvent

    fun start() {
        if(movies.isEmpty()) {
            dataLoading.set(true)
            popMoviesRepository.getMovies(object : PopMoviesDataSource.LoadPopMoviesCallback {
                override fun onPopMoviesLoaded(popMovies: List<Movie>) {
                    dataLoading.set(false)
                    movies.clear()
                    movies.addAll(popMovies)
                }

                override fun onDataNotAvailable() {

                }
            })
        }
    }
}