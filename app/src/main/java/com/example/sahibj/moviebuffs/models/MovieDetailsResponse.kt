package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibj on 10/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class MovieDetailsResponse {

    @JsonProperty("original_title")
    var originalTitle: String? = null
}