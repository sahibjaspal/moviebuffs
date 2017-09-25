package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibj on 9/24/17.
 */
class Movie {

    @JsonProperty("vote_count")
    var voteCount: Int? = null

    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("video")
    var video: Boolean? = null

    @JsonProperty("vote_average")
    var voteAverage: Float? = null

    @JsonProperty("title")
    var title: String? = null

    @JsonProperty("popularity")
    var popularity: Float? = null

    @JsonProperty("poster_path")
    var posterPath: String? = null

    @JsonProperty("original_language")
    var originalLanguage: String? = null

    @JsonProperty("original_title")
    var originalTitle: String? = null

    @JsonProperty("genre_ids")
    var genreIds: List<Int>? = null

    @JsonProperty("backdrop_path")
    var backdropPath: String? = null

    @JsonProperty("adult")
    var adult: Boolean? = null

    @JsonProperty("overview")
    var overview: String? = null

    @JsonProperty("release_date")
    var releaseDate: String? = null

    fun getVoteAverage(): String {
        return voteAverage.toString()
    }
}