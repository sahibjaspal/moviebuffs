package com.example.sahibj.moviebuffs.dagger

import com.example.sahibj.moviebuffs.activities.MovieDetailActivity
import com.example.sahibj.moviebuffs.data.remote.PopMovieRemoteDataSource
import com.example.sahibj.moviebuffs.fragments.PopularMoviesFragment
import com.example.sahibj.moviebuffs.viewmodels.HorizontalRVViewModel
import com.example.sahibj.moviebuffs.viewmodels.MovieCastViewModel
import com.example.sahibj.moviebuffs.viewmodels.MovieDetailViewModel
import com.example.sahibj.moviebuffs.viewmodels.PopMoviesViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sahibj on 9/25/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, MovieServiceMod::class))
interface AppComponent {

    fun inject(activity: PopMoviesViewModel)
    fun inject(movieDetailActivity: MovieDetailActivity)
    fun inject(popMovieRemoteDataSource: PopMovieRemoteDataSource)
    fun inject(movieDetailViewModel: MovieDetailViewModel)
    fun inject(horizontalRVViewModel: HorizontalRVViewModel)
    fun inject(movieCastViewModel: MovieCastViewModel)
}