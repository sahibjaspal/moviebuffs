package com.example.sahibj.moviebuffs.data.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.sahibj.moviebuffs.fragments.PopularMoviesFragment
import java.util.*

/**
 * Created by sahibjaspal on 1/27/18.
 */
class MovieFragmentPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    private val tabTiles: ArrayList<String> =
            ArrayList(Arrays.asList("Now Playing", "Upcoming", "Popular", "Top Rated"))


    override fun getItem(position: Int): Fragment {
        return PopularMoviesFragment.getInstance(tabTiles[position])
    }

    override fun getCount(): Int {
        return tabTiles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTiles[position]
    }
}