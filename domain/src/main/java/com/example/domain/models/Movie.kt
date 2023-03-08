package com.example.domain.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title:String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val imageUrl: String? = null,
    @SerializedName("overview") val overview: String? = null,
    val image: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }
}

data class MovieList(
    @SerializedName("results") var movieList: List<Movie>? = null
)

