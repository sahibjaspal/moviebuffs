package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.liveevents.SingleLiveEvent

/**
 * Created by sahibj on 10/1/17.
 */
class PopMoviesViewModel(app: Application): AndroidViewModel(app) {

    init {
        (app as MovieBuffApplication).getNetComponent()?.inject(this)
    }

    private val openMovieEvent = SingleLiveEvent<Int>()

    fun getOpenMovieEvent(): SingleLiveEvent<Int> = openMovieEvent
}