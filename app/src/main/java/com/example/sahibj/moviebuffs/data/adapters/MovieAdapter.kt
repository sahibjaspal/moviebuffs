package com.example.sahibj.moviebuffs.data.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.LayoutMovieItemBinding
import com.example.sahibj.moviebuffs.misc.IMAGE_BASE_URL_POSTER
import com.example.sahibj.moviebuffs.models.Movie

/**
 * Created by sahibj on 9/24/17.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    companion object {
        val TAG = MovieAdapter.javaClass.simpleName
    }

    val movies: ObservableList<Movie>
    private val fragmentType: String

    constructor(movies: ObservableArrayList<Movie>, fragmentType: String) : super() {
        this.movies = movies
        this.fragmentType = fragmentType
        this.movies.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableArrayList<Movie>>(){
            override fun onItemRangeRemoved(p0: ObservableArrayList<Movie>?, p1: Int, p2: Int) {
                Log.v(TAG, "item range removed called")
            }

            override fun onChanged(p0: ObservableArrayList<Movie>?) {
                Log.v(TAG, "onchanged called")

            }

            override fun onItemRangeChanged(p0: ObservableArrayList<Movie>?, p1: Int, p2: Int) {
                Log.v(TAG, "item range changed called")
            }

            override fun onItemRangeInserted(p0: ObservableArrayList<Movie>?, p1: Int, p2: Int) {
                Log.v(TAG, "item range inserted called")
                notifyDataSetChanged()
            }

            override fun onItemRangeMoved(p0: ObservableArrayList<Movie>?, p1: Int, p2: Int, p3: Int) {
                Log.v(TAG, "item range moved called")
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<LayoutMovieItemBinding>(
                LayoutInflater.from(parent.context), R.layout.layout_movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movie = movie
        holder.binding.movieImage.setOnClickListener { view -> holder.binding.movie }
        Glide.with(holder.binding.movieImage.context)
                .load(IMAGE_BASE_URL_POSTER + movie.posterPath)
                .into(holder.binding.movieImage)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val binding: LayoutMovieItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}