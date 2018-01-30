package com.example.sahibj.moviebuffs.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.data.adapters.MovieFragmentPagerAdapter
import com.example.sahibj.moviebuffs.databinding.FragmentMoviesMainBinding

/**
 * Created by sahibjaspal on 1/27/18.
 */
class MoviesMainFragment : Fragment() {

    companion object {
        val TAG = MoviesMainFragment::class.java.name
    }

    lateinit var binding: FragmentMoviesMainBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_main, container,
                false)

        activity.title = "Movies"

        binding.movieViewPager.adapter = MovieFragmentPagerAdapter(activity.supportFragmentManager)
        binding.movieViewPager.offscreenPageLimit = 2
        binding.tablayout.setupWithViewPager(binding.movieViewPager)
        return binding.root
    }
}