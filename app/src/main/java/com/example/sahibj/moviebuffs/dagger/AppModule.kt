package com.example.sahibj.moviebuffs.dagger

import android.app.Application
import com.example.sahibj.moviebuffs.data.PopMoviesRepository
import com.example.sahibj.moviebuffs.data.remote.PopMovieRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sahibj on 9/25/17.
 */
@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesPopMoviesRepository(popMovieRemoteDataSource: PopMovieRemoteDataSource)
            : PopMoviesRepository {
        return PopMoviesRepository(popMovieRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesPopMovieRemoteDataSource(application: Application): PopMovieRemoteDataSource {
        return PopMovieRemoteDataSource(application)
    }
}