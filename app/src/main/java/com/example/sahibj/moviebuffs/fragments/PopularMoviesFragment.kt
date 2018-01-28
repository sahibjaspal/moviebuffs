package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.activities.MovieDetailActivity
import com.example.sahibj.moviebuffs.data.adapters.MovieAdapter
import com.example.sahibj.moviebuffs.databinding.PopularMoviesFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_FRAGMENT_TYPE
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.viewmodels.*

/**
 * Created by sahibj on 9/30/17.
 */
class PopularMoviesFragment : LifecycleFragment() {

    lateinit var binding: PopularMoviesFragmentBinding
    lateinit var fragmentType: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.popular_movies_fragment, container,
                false)

        fragmentType = arguments.getString(EXTRA_FRAGMENT_TYPE)

        binding.viewModel = ViewModelProviders.of(activity)
                .get(ViewModelFactory().getViewModelClass(fragmentType))

        binding.viewModel?.getOpenMovieEvent()?.observe(this,
                Observer { movieId -> openMovieDetails(movieId) })

        setupRecyclerView()


        return binding.root
    }

    private fun setupRecyclerView() {

        //layout manager for Popular Movies
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false)

        if (fragmentType == "Popular") {
            layoutManager = GridLayoutManager(activity, 2)
        }

        binding.movies.layoutManager = layoutManager
        binding.movies.adapter = MovieAdapter(binding.viewModel!!, fragmentType)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.start(fragmentType)
    }

    private fun openMovieDetails(movieId: Int?) {
        val movieDetailIntent = Intent(activity, MovieDetailActivity::class.java)
        movieDetailIntent.putExtra(EXTRA_MOVIE_ID, movieId)
        startActivity(movieDetailIntent)
    }

    companion object {
        val TAG = PopularMoviesFragment::class.java.name
        fun getInstance(fragmentType: String): Fragment {
            val popularMoviesFragment = PopularMoviesFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_FRAGMENT_TYPE, fragmentType)
            popularMoviesFragment.arguments = bundle
            return popularMoviesFragment
        }
    }
}