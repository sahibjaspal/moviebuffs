package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.liveevents.SingleLiveEvent
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.services.MovieService
import javax.inject.Inject

/**
 * Created by sahibj on 10/1/17.
 */
class PopMoviesViewModel(app: Application): AndroidViewModel(app) {

    init {
        (app as MovieBuffApplication).getNetComponent()?.inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository

    private val openMovieEvent = SingleLiveEvent<Int>()
    val movies: ObservableList<Movie> = ObservableArrayList()

    fun getOpenMovieEvent(): SingleLiveEvent<Int> = openMovieEvent

    fun start() {
        popMoviesRepository.getTasks(object: PopMoviesDataSource.LoadPopMoviesCallback{
            override fun onPopMoviesLoaded(popMovies: List<Movie>) {
                movies.clear()
                movies.addAll(popMovies)
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}