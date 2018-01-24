package com.example.sahibj.moviebuffs.data.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.SimilarMovieItemBinding
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.viewmodels.SimilarMoviesViewModel

/**
 * Created by sahibjaspal on 1/22/18.
 */
class SimilarMoviesAdapter(var viewModel:SimilarMoviesViewModel)
    : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    private lateinit var binding: SimilarMovieItemBinding
    val similarMovies: ObservableList<Movie> = viewModel.similarMovies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate<SimilarMovieItemBinding>(
                LayoutInflater.from(parent.context), R.layout.similar_movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.movie = similarMovies[position]
        val movie = similarMovies[position]

        Glide.with(holder.movieView.context)
                .load("http://image.tmdb.org/t/p/w780" + movie.posterPath)
                .into(holder.movieView)

    }

    override fun getItemCount(): Int {
        return similarMovies.size
    }

    inner class ViewHolder(binding: SimilarMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        internal var movieView: ImageView = binding.similarMoviePoster
    }
}