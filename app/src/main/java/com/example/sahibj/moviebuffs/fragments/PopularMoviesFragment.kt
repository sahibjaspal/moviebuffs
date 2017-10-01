package com.example.sahibj.moviebuffs.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sahibj.moviebuffs.MovieBuffApplication
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.data.MovieAdapter
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.services.MovieService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by sahibj on 9/30/17.
 */
class PopularMoviesFragment : Fragment() {

    lateinit var movieRecyclerView: RecyclerView

    @Inject
    lateinit var movieService: MovieService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.popular_movies_fragment, container, false)

        (activity.application as MovieBuffApplication).getNetComponent()?.inject(this)

        movieRecyclerView = root.findViewById<RecyclerView>(R.id.movies)
        movieRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        movieService.getPopularMovies("popular")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ popularMovieResponse ->
                    Log.v(TAG, "Retrieved popular movies")
                    popularMovieResponse.movies?.let { bindMovies(it) }
                }, { t: Throwable ->
                    Log.e(TAG, "Failed to retrieve popular movies", t)
                })
    }

    private fun bindMovies(movies: List<Movie>) {
        movieRecyclerView.adapter = MovieAdapter(movies)
    }

    companion object {
        val TAG = PopularMoviesFragment::class.java.name
    }
}