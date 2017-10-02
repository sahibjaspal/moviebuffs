package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.activities.MovieDetailActivity
import com.example.sahibj.moviebuffs.data.MovieAdapter
import com.example.sahibj.moviebuffs.databinding.PopularMoviesFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.services.MovieService
import com.example.sahibj.moviebuffs.viewmodels.PopMoviesViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by sahibj on 9/30/17.
 */
class PopularMoviesFragment : LifecycleFragment() {

    @Inject
    lateinit var movieService: MovieService

    lateinit var binding: PopularMoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.popular_movies_fragment, container, false)

        (activity.application as MovieBuffApplication).getNetComponent()?.inject(this)

        binding.viewModel = ViewModelProviders.of(activity).get(PopMoviesViewModel::class.java)

        binding.viewModel?.getOpenMovieEvent()?.observe(this, Observer { movieId -> openMovieDetails(movieId) })

        binding.movies.layoutManager = GridLayoutManager(activity, 2)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        movieService.getPopularMovies("popular")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ popularMovieResponse ->
                    Log.v(TAG, "Retrieved popular movies")
                    popularMovieResponse.movies?.let { bindMovies(it) }
                }, { t: Throwable ->
                    Log.e(TAG, "Failed to retrieve popular movies", t)
                })
    }

    private fun bindMovies(movies: List<Movie>) {
        binding.movies.adapter = MovieAdapter(movies, binding.viewModel)
    }

    private fun openMovieDetails(movieId: Int?) {
        val movieDetailIntent = Intent(activity, MovieDetailActivity::class.java)
        movieDetailIntent.putExtra(EXTRA_MOVIE_ID, movieId)
        startActivity(movieDetailIntent)
    }

    companion object {
        val TAG = PopularMoviesFragment::class.java.name
    }
}