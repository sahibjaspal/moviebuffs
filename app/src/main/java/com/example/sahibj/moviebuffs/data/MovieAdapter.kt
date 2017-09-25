package com.example.sahibj.moviebuffs.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.models.Movie

/**
 * Created by sahibj on 9/24/17.
 */
class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        Glide.with(holder.movieView.context)
                .load("http://image.tmdb.org/t/p/w780" + movie.posterPath)
                .into(holder.movieView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var movieView: ImageView = view.findViewById<ImageView>(R.id.movie_image)
    }
}
