package com.example.sahibj.moviebuffs

import android.app.Application
import com.example.sahibj.moviebuffs.dagger.AppComponent

/**
 * Created by sahibj on 9/25/17.
 */
class MovieBuffApplication: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()



    }

    fun getNetComponent(): AppComponent = appComponent
}