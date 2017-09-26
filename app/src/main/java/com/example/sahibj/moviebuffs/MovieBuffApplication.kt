package com.example.sahibj.moviebuffs

import android.app.Application
import com.example.sahibj.moviebuffs.dagger.AppComponent
import com.example.sahibj.moviebuffs.dagger.AppModule
import com.example.sahibj.moviebuffs.dagger.NetworkModule

/**
 * Created by sahibj on 9/25/17.
 */
class MovieBuffApplication: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .networkModule(NetworkModule("https://api.themoviedb.org/3/"))
//                .build()

    }

    fun getNetComponent(): AppComponent = appComponent
}