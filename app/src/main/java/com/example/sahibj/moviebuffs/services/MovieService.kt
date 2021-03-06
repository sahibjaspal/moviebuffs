package com.example.sahibj.moviebuffs.services

import com.example.sahibj.moviebuffs.models.MovieDetailsResponse
import com.example.sahibj.moviebuffs.models.PopularMovieResponse
import com.example.sahibj.moviebuffs.models.AltMovieResponse
import com.example.sahibj.moviebuffs.models.MovieCastResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by sahibj on 9/24/17.
 */
interface MovieService {

    @GET("movie/{type}")
    fun getPopularMovies(@Path("type") type: String): Observable<PopularMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Observable<MovieDetailsResponse>

    @GET("movie/{movie_id}/{type}")
    fun getAltMovies(@Path("movie_id") movieId: Int, @Path("type") type: String):
            Observable<AltMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCast(@Path("movie_id") movieId: Int): Observable<MovieCastResponse>
}