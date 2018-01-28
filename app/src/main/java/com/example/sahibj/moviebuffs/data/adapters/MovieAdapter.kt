package com.example.sahibj.moviebuffs.data.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.LayoutMovieItemBinding
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.useractions.MovieItemUserActionsListener
import com.example.sahibj.moviebuffs.viewmodels.PopMoviesViewModel

/**
 * Created by sahibj on 9/24/17.
 */
class MovieAdapter(private val viewModel: PopMoviesViewModel, private val fragmentType: String)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>(), MovieItemUserActionsListener {

    private lateinit var binding: LayoutMovieItemBinding
    val movies: ObservableList<Movie> = viewModel.movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate<LayoutMovieItemBinding>(
                LayoutInflater.from(parent.context), R.layout.layout_movie_item, parent, false)

        binding.listener = this

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.movie = movies[position]
        val movie = movies[position]
        Glide.with(holder.movieView.context)
                .load("http://image.tmdb.org/t/p/w780" + movie.posterPath)
                .into(holder.movieView)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(binding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        internal var movieView: ImageView = binding.movieImage
    }

    override fun onMovieItemClicked(movie:Movie) {
        viewModel.getOpenMovieEvent().value = movie.id
    }
}