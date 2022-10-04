package com.example.flixsterplus_part2

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/** The Model stores a single movie from themoviedb API.
 * SerializedName tags match the JSON response for the object. */

/*
@Keep
@Serializable
data class searchMovies(
    @SerializedName("results")
    val results : MovieArray?
) : java.io.Serializable

@Keep
@Serializable
data class MovieArray(
    @SerializedName("results")
    val results : Movie?
)

 */

@Keep
@Serializable
class Movie {
    @JvmField
    @SerializedName("original_title")
    var title: String? = null

    @SerializedName("poster_path")
    var movieImageUrl: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("vote_average")
    var voteAverage: String? = null
}