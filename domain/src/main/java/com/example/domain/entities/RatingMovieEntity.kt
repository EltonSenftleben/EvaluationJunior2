package com.example.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Movie
import com.google.gson.annotations.SerializedName

@Entity(tableName = "RatingMovie")
data class RatingMovieEntity(
    @PrimaryKey
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

        other as RatingMovieEntity

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }
}
