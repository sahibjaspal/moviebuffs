package com.example.sahibj.moviebuffs.fragments

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
import com.example.sahibj.moviebuffs.utils.FragmentUtils
import com.example.sahibj.moviebuffs.viewmodels.MovieDetailViewModel

/**
 * Created by sahibjaspal on 1/20/18.
 */
class MovieDetailFragment : Fragment() {

    lateinit var binding: MovieDetailFragmentBinding
    var movieId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container,
                false)

        binding.viewModel = ViewModelProviders.of(activity).get(MovieDetailViewModel::class.java)

        movieId = arguments.getInt(EXTRA_MOVIE_ID)

        FragmentUtils.addFragment(activity.supportFragmentManager,
                HorizontalRVFragment.getInstance(movieId, "similar"),
                HorizontalRVFragment.TAG + "similar",
                R.id.similar_movie_container)

        FragmentUtils.addFragment(activity.supportFragmentManager,
                HorizontalRVFragment.getInstance(movieId, "recommendations"),
                HorizontalRVFragment.TAG + "recommendations",
                R.id.recommendations_movie_container)

        FragmentUtils.addFragment(activity.supportFragmentManager,
                CastFragment.getInstance(movieId), CastFragment.TAG, R.id.movie_cast_container)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.start(movieId)
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