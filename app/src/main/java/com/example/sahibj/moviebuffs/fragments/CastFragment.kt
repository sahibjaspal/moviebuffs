package com.example.sahibj.moviebuffs.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.FragmentCastBinding
import com.example.sahibj.moviebuffs.misc.EXTRA_MOVIE_ID
import com.example.sahibj.moviebuffs.misc.EXTRA_RV_TYPE
import com.example.sahibj.moviebuffs.viewmodels.MovieCastViewModel

/**
 * Created by sahibjaspal on 1/27/18.
 */
class CastFragment : Fragment() {

    lateinit var binding:FragmentCastBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cast,
                container, false)

        binding.viewModel = ViewModelProviders.of(this).get(MovieCastViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        binding.viewModel.start()
    }

    companion object {
        val TAG = CastFragment::class.java.name

        fun getInstance(movieId: Int, type: String): Fragment {
            val castFragment = CastFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_MOVIE_ID, movieId)
            bundle.putString(EXTRA_RV_TYPE, type)
            castFragment.arguments = bundle
            return castFragment
        }
    }
}