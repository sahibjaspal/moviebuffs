package com.example.sahibj.moviebuffs.dagger

import android.app.Application
import com.example.sahibj.moviebuffs.services.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by sahibj on 9/25/17.
 */
@Module
public class MovieServiceMod {

    @Provides
    @Singleton
    public fun providesMovieService(restAdapter: Retrofit): MovieService {
        return restAdapter.create(MovieService::class.java)
    }
}