package com.example.sahibj.moviebuffs.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.sahibj.moviebuffs.R

/**
 * Created by sahibjaspal on 1/20/18.
 */

object FragmentUtils {

    fun addFragment(supportFragmentManager: FragmentManager,
                    fragmentToAdd: Fragment,
                    tag: String) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentFrame, fragmentToAdd, tag).commit()
        }
    }
}
