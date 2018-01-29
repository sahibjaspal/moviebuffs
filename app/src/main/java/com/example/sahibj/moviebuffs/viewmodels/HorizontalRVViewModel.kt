package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.AltMovieResponse
import javax.inject.Inject

/**
 * Created by sahibjaspal on 1/22/18.
 */
class HorizontalRVViewModel(app: Application) : AndroidViewModel(app) {

    init {
        (app as MovieBuffApplication).getNetComponent().inject(this)
    }

    @Inject
    lateinit var popMoviesRepository: PopMoviesRepository

    val altMovies: ObservableList<Movie> = ObservableArrayList()
    val dataLoading = ObservableBoolean(false)
    val label : ObservableField<String> = ObservableField()

    fun loadAltMovies(movieId: Int, type: String) {
        setLabel(type)
        if (altMovies.isEmpty()) {
            dataLoading.set(true)
            popMoviesRepository.getAltMovies(movieId, type, object : PopMoviesDataSource.AltMoviesCallback {
                override fun altMoviesLoaded(altMovieResponse: AltMovieResponse) {
                    dataLoading.set(false)
                    altMovies.clear()
                    altMovies.addAll(altMovieResponse.altMovies)
                }

                override fun onDataNotAvailable() {

                }
            })
        }
    }

    private fun setLabel(type: String) {
        if(type == "similar"){
            label.set("Similar Movies")
        }else{
            label.set("Recommended Movies")
        }
    }
}