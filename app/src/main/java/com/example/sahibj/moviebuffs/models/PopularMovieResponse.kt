package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibj on 9/24/17.
 */
class PopularMovieResponse {

    @JsonProperty("page")
    var page: Int? = null

    @JsonProperty("total_results")
    var totalResults: Int? = null

    @JsonProperty("total_pages")
    var totalPages: Int? = null

    @JsonProperty("results")
    var movies: List<Movie> = ArrayList()
}