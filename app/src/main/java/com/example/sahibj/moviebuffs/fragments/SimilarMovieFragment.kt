package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.data.adapters.SimilarMoviesAdapter
import com.example.sahibj.moviebuffs.databinding.SimilarMovieFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.viewmodels.SimilarMoviesViewModel

/**
 * Created by sahibjaspal on 1/22/18.
 */
class SimilarMovieFragment : LifecycleFragment() {

    lateinit var binding: SimilarMovieFragmentBinding
    var movieId: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.similar_movie_fragment,
                container, false)

        movieId = arguments.getInt(EXTRA_MOVIE_ID)

        binding.viewModel = ViewModelProviders.of(activity).get(SimilarMoviesViewModel::class.java)

        binding.similarMovies.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        binding.similarMovies.adapter = SimilarMoviesAdapter(binding.viewModel!!)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.start(movieId)
    }

    companion object {
        val TAG = SimilarMovieFragment::class.java.name

        fun getInstance(movieId: Int): Fragment {
            val similarMovieFragment = SimilarMovieFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, movieId)
            similarMovieFragment.arguments = bundle
            return similarMovieFragment
        }
    }
}