package com.example.sahibj.moviebuffs.data.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.AltMovieItemBinding
import com.example.sahibj.moviebuffs.misc.IMAGE_BASE_URL_POSTER
import com.example.sahibj.moviebuffs.models.Movie
import com.example.sahibj.moviebuffs.viewmodels.HorizontalRVViewModel

/**
 * Created by sahibjaspal on 1/22/18.
 */
class HorizontalRVMoviesAdapter(var viewModel: HorizontalRVViewModel)
    : RecyclerView.Adapter<HorizontalRVMoviesAdapter.ViewHolder>() {

    private lateinit var binding: AltMovieItemBinding
    val similarMovies: ObservableList<Movie> = viewModel.altMovies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.alt_movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.movie = similarMovies[position]
        val movie = similarMovies[position]

        Glide.with(holder.movieView.context)
                .load(IMAGE_BASE_URL_POSTER + movie.posterPath)
                .into(holder.movieView)
    }

    override fun getItemCount(): Int {
        return similarMovies.size
    }

    inner class ViewHolder(binding: AltMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        internal var movieView: ImageView = binding.altMoviePoster
    }
}