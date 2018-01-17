package com.example.sahibj.moviebuffs.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.services.MovieService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MovieBuffApplication).getNetComponent().inject(this)

        setContentView(R.layout.activity_movie_detail)

        if(intent.hasExtra(EXTRA_MOVIE_ID)){
            val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

            getMovieDetails(movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {

        movieService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieDetailResponse ->
                    Log.v(TAG, "Retrieved movie detailss")
                }, { t: Throwable ->
                    Log.e(TAG, "Failed to retrieve movie details", t)
                })
    }

    companion object {
        val TAG = MovieDetailActivity::class.java.name
    }
}
