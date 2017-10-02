package com.example.sahibj.moviebuffs.useractions

import com.example.sahibj.moviebuffs.models.Movie

/**
 * Created by sahibj on 10/1/17.
 */
interface MovieItemUserActionsListener {

    fun onMovieItemClicked(movie: Movie)
}