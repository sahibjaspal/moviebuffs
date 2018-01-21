package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.MovieDetailFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.viewmodels.MovieDetailViewModel

/**
 * Created by sahibjaspal on 1/20/18.
 */
class MovieDetailFragment : LifecycleFragment() {

    lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container,
                false)

        binding.viewModel = ViewModelProviders.of(activity).get(MovieDetailViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.start(arguments.getInt(EXTRA_MOVIE_ID))
    }

    companion object {
        val TAG = MovieDetailFragment::class.java.name

        fun getInstance(movieId: Int): Fragment {
            val movieDetailFragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, movieId)
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
        }
    }
}