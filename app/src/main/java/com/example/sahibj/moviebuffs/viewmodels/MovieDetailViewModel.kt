package com.example.sahibj.moviebuffs.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.data.PopMoviesDataSource
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.misc.IMAGE_BASE_URL_BACKDROP
import com.example.sahibj.moviebuffs.misc.IMAGE_BASE_URL_POSTER
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

    var cachedMovieDetails: MovieDetailsResponse? = null
    val backdropImageUrl: ObservableField<String> = ObservableField()
    val posterImageUrl: ObservableField<String> = ObservableField()
    val title: ObservableField<String> = ObservableField()
    val releaseDate: ObservableField<String> = ObservableField()
    val tagline: ObservableField<String> = ObservableField()
    val overview: ObservableField<String> = ObservableField()

    fun start(movieId: Int) {
        if (cachedMovieDetails == null) {

            popMoviesRepository.getMovie(movieId, object : PopMoviesDataSource.LoadMovieCallback {
                override fun onMovieLoaded(movieDetailResponse: MovieDetailsResponse) {
                    cachedMovieDetails = movieDetailResponse
                    backdropImageUrl.set(IMAGE_BASE_URL_BACKDROP + movieDetailResponse.backdropPath)
                    posterImageUrl.set(IMAGE_BASE_URL_POSTER + movieDetailResponse.posterPath)
                    title.set(movieDetailResponse.title)
                    releaseDate.set(movieDetailResponse.releaseDate)
                    tagline.set(movieDetailResponse.tagline)
                    overview.set(movieDetailResponse.overview)
                }

                override fun onDataNotAvailable() {

                }
            })
        }
    }
}