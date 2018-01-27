package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibjaspal on 1/27/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class MovieCastResponse {

    @JsonProperty("id")
    var movieId: String? = null
    @JsonProperty("cast")
    var castList: List<Cast> = ArrayList()

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Cast {
        var name: String? = null
        @JsonProperty("id")
        var personId: String? = null
        @JsonProperty("profile_path")
        var personImage: String? = null
    }
}