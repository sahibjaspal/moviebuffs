package com.example.sahibj.moviebuffs.services

import com.example.sahibj.moviebuffs.models.MovieDetailsResponse
import com.example.sahibj.moviebuffs.models.PopularMovieResponse
import com.example.sahibj.moviebuffs.models.SimilarMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by sahibj on 9/24/17.
 */
interface MovieService {

    @GET("movie/{sort}")
    fun getPopularMovies(@Path("sort") sort: String): Observable<PopularMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId:Int): Observable<MovieDetailsResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId:Int): Observable<SimilarMovieResponse>
}