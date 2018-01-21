package com.example.sahibj.moviebuffs.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.fragments.MovieDetailFragment
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.services.MovieService
import com.example.sahibj.moviebuffs.utils.FragmentUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MovieBuffApplication).getNetComponent().inject(this)

        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(intent.hasExtra(EXTRA_MOVIE_ID)){
            val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

            FragmentUtils.addFragment(supportFragmentManager,
                    MovieDetailFragment.getInstance(movieId), MovieDetailFragment.TAG)
        }else{
            throw IllegalStateException("Cannot start MovieDetail Activity without a movieId")
        }
    }

    companion object {
        val TAG = MovieDetailActivity::class.java.name
    }
}
