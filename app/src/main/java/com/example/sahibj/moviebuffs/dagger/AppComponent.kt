package com.example.sahibj.moviebuffs.dagger

import com.example.sahibj.moviebuffs.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sahibj on 9/25/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
public interface AppComponent {

    fun inject(activity: MainActivity)
}