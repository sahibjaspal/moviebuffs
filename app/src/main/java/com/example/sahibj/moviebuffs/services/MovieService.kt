package com.example.sahibj.moviebuffs.services

import com.example.sahibj.moviebuffs.models.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by sahibj on 9/24/17.
 */
interface MovieService {

    @GET("movie/{sort}")
    fun getPopularMovies(@Path("sort") sort: String, @Query("api_key") api_key: String): Call<PopularMovieResponse>
}