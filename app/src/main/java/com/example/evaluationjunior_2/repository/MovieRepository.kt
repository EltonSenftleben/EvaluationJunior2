package com.example.evaluationjunior_2.repository

import com.example.domain.models.Movie
import com.example.domain.models.MovieList
import com.example.web.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopular(key : String) : Flow<NetworkResult<MovieList>>

    fun getUpcoming(key : String) : Flow<NetworkResult<MovieList>>

    fun getTopRate(key : String) : Flow<NetworkResult<MovieList>>

    fun getMovie(id: Int): Flow<NetworkResult<Movie>>
}