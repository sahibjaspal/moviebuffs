package com.example.sahibj.moviebuffs.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by sahibj on 10/1/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class MovieDetailsResponse {

    @JsonProperty("original_language")
    val originalLanguage: String? = null

    @JsonProperty("imdb_id")
    val imdbId: String? = null

    @JsonProperty("video")
    val video: Boolean? = null

    @JsonProperty("title")
    val title: String? = null

    @JsonProperty("backdrop_path")
    val backdropPath: String? = null

    @JsonProperty("revenue")
    val revenue: Int? = null

    @JsonProperty("genres")
    val genres: List<GenresItem?>? = null

    @JsonProperty("popularity")
    val popularity: Double? = null

    @JsonProperty("id")
    val id: Int? = null

    @JsonProperty("vote_count")
    val voteCount: Int? = null

    @JsonProperty("budget")
    val budget: Int? = null

    @JsonProperty("overview")
    val overview: String? = null

    @JsonProperty("original_title")
    val originalTitle: String? = null

    @JsonProperty("runtime")
    val runtime: Int? = null

    @JsonProperty("poster_path")
    val posterPath: String? = null

    @JsonProperty("release_date")
    val releaseDate: String? = null

    @JsonProperty("vote_average")
    val voteAverage: Double? = null

    @JsonProperty("tagline")
    val tagline: String? = null

    @JsonProperty("adult")
    val adult: Boolean? = null

    @JsonProperty("homepage")
    val homepage: String? = null

    @JsonProperty("status")
    val status: String? = null

    class GenresItem {

        @JsonProperty("name")
        val name: String? = null

        @JsonProperty("id")
        val id: Int? = null
    }
}