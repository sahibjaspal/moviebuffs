package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibjaspal on 1/22/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class AltMovieResponse {

    var page:Int? = null
    @JsonProperty("results")
    var altMovies:List<Movie> = ArrayList()
}