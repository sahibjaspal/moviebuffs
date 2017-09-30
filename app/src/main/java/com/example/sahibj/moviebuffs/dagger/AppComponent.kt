package com.example.sahibj.moviebuffs.dagger

import com.example.sahibj.moviebuffs.activities.MainActivity
import com.example.sahibj.moviebuffs.services.MovieService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sahibj on 9/25/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, MovieServiceMod::class))
interface AppComponent {

    fun inject(activity: MainActivity)
}