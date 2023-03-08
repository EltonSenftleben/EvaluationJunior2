package com.example.evaluationjunior_2.repository

import androidx.lifecycle.LiveData
import com.example.domain.entities.PopularMovieEntity
import com.example.domain.entities.RatingMovieEntity
import com.example.domain.entities.UpcomingMovieEntity
import com.example.domain.models.Movie

interface LocalMovieRepository {

    //PopularMovies
    fun getAllPopularMovies(): LiveData<List<PopularMovieEntity>>

    fun getPopularMovie(id: Int): LiveData<PopularMovieEntity>

    suspend fun insertAllPopulars(movie: List<PopularMovieEntity>)

    suspend fun insertPopular(movie: PopularMovieEntity)

    //UpcomingMovies
    fun getAllUpcomingMovies(): LiveData<List<UpcomingMovieEntity>>

    fun getUpcomingMovie(id: Int): LiveData<UpcomingMovieEntity>

    suspend fun insertAllUpcomings(movie: List<UpcomingMovieEntity>)

    suspend fun insertUpcoming(movie: UpcomingMovieEntity)

    //RatingMovies
    fun getAllRatingMovies(): LiveData<List<RatingMovieEntity>>

    fun getRatingMovie(id: Int): LiveData<RatingMovieEntity>

    suspend fun insertAllRatings(movie: List<RatingMovieEntity>)

    suspend fun insertRating(movie: RatingMovieEntity)

}