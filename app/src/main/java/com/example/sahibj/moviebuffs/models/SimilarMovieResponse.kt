package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibjaspal on 1/22/18.
 */
class SimilarMovieResponse {

    var page:Int? = null
    @JsonProperty("results")
    var similarMovies :List<Movie> = ArrayList()
}