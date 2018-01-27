package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.models.MovieCastResponse
import javax.inject.Inject

/**
 * Created by sahibjaspal on 1/27/18.
 */
class MovieCastViewModel(var app: Application) : AndroidViewModel(app) {

    companion object {
        val TAG = MovieCastViewModel::class.java.simpleName
    }

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository
    val castList:ObservableList<MovieCastResponse.Cast> = ObservableArrayList()


    fun start(movieId: Int) {
        popMoviesRepository.getMovieCast(movieId, object: PopMoviesDataSource.LoadMovieCastCallback{
            override fun movieCastLoaded(movieCastResponse: MovieCastResponse) {
                castList.addAll(movieCastResponse.castList)
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}