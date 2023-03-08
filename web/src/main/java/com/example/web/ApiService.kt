package com.example.web

import com.example.domain.models.Movie
import com.example.domain.models.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopular(@Query("api_key") key : String) : Response<MovieList>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") key : String) : Response<MovieList>

    @GET("movie/top_rated")
    suspend fun getTopRate(@Query("api_key") key : String) : Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<Movie>
}