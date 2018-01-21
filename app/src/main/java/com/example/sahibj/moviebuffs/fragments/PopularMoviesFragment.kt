package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.activities.MovieDetailActivity
import com.example.sahibj.moviebuffs.data.MovieAdapter
import com.example.sahibj.moviebuffs.databinding.PopularMoviesFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.viewmodels.PopMoviesViewModel

/**
 * Created by sahibj on 9/30/17.
 */
class PopularMoviesFragment : LifecycleFragment() {

    lateinit var binding: PopularMoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.popular_movies_fragment, container,
                false)

        binding.viewModel = ViewModelProviders.of(activity).get(PopMoviesViewModel::class.java)

        binding.viewModel?.getOpenMovieEvent()?.observe(this,
                Observer { movieId -> openMovieDetails(movieId) })

        binding.movies.layoutManager = GridLayoutManager(activity, 2)
        binding.movies.adapter = MovieAdapter(binding.viewModel!!)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.start()
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