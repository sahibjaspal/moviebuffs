package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.data.adapters.HorizontalRVMoviesAdapter
import com.example.sahibj.moviebuffs.databinding.HorizontalRvFragmentBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.misc.EXTRA_RV_TYPE
import com.example.sahibj.moviebuffs.viewmodels.HorizontalRVViewModel

/**
 * Created by sahibjaspal on 1/22/18.
 */
class HorizontalRVFragment : Fragment() {

    lateinit var binding: HorizontalRvFragmentBinding
    var movieId: Int = 0
    lateinit var type: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.horizontal_rv_fragment,
                container, false)

        movieId = arguments.getInt(EXTRA_MOVIE_ID)
        type = arguments.getString(EXTRA_RV_TYPE)

        binding.viewModel = ViewModelProviders.of(this).get(HorizontalRVViewModel::class.java)

        binding.altMovies.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        binding.altMovies.adapter = HorizontalRVMoviesAdapter(binding.viewModel!!)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel?.loadAltMovies(movieId, type)
    }

    companion object {
        val TAG = HorizontalRVFragment::class.java.name

        fun getInstance(movieId: Int, type: String): Fragment {
            val horizontalRVFragment = HorizontalRVFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, movieId)
            bundle.putString(EXTRA_RV_TYPE, type)
            horizontalRVFragment.arguments = bundle
            return horizontalRVFragment
        }
    }
}