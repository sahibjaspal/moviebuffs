package com.example.sahibj.moviebuffs.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.data.MovieAdapter
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.models.PopularMovieResponse
import com.example.sahibj.moviebuffs.services.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val API_KEY = "b2cacc567a4d73b94ca6d1e02aee273e"
    private lateinit var movieService: MovieService
    private val SORT = "popular"
    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

        movieService = retrofit.create(MovieService::class.java)

        setupViews()

        getPopularMovies()
    }

    private fun setupViews() {
        movieRecyclerView = findViewById<RecyclerView>(R.id.movies)

        movieRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
    }

    private fun getPopularMovies() {
        movieService.getPopularMovies(SORT, API_KEY)
                .enqueue(object : Callback<PopularMovieResponse> {
                    override fun onResponse(call: Call<PopularMovieResponse>,
                                            response: Response<PopularMovieResponse>) {

                        if (response.isSuccessful) {
                            Log.v(TAG, "Successfully retrieved popular movies")
                            if (response.body() != null) {
                                response.body()?.movies?.let {
                                    bindMovies(it)
                                }
                            }
                        } else {
                            Log.e(TAG, "Failed to retrieve popular movies")
                        }
                    }

                    override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                        Log.e(TAG, "Failed to retrieve popular movies", t)
                    }
                })
    }

    private fun bindMovies(movies: List<Movie>) {
        movieRecyclerView.adapter = MovieAdapter(movies)
    }

    companion object {
        private val TAG = MainActivity.javaClass.simpleName
    }
}
