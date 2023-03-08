package com.example.examen.repository

import androidx.lifecycle.LiveData
import com.example.database.DBMovie
import com.example.domain.entities.PopularMovieEntity
import com.example.domain.entities.RatingMovieEntity
import com.example.domain.entities.UpcomingMovieEntity
import com.example.domain.models.Movie
import com.example.evaluationjunior_2.repository.LocalMovieRepository
import javax.inject.Inject

class LocalMovieRepositoryImp @Inject constructor(
    private val dbMovie: DBMovie
): LocalMovieRepository {
    //PopularMovie
    override fun getAllPopularMovies(): LiveData<List<PopularMovieEntity>> {
        return  dbMovie.popularMovieDao().getAllMovies()
    }

    override fun getPopularMovie(id: Int): LiveData<PopularMovieEntity> {
        return dbMovie.popularMovieDao().getMovie(id)
    }

    override suspend fun insertAllPopulars(movie: List<PopularMovieEntity>) {
        return dbMovie.popularMovieDao().insertAll(movie)
    }

    override suspend fun insertPopular(movie: PopularMovieEntity) {
        return dbMovie.popularMovieDao().insert(movie)
    }

    //UpcomingMovie
    override fun getAllUpcomingMovies(): LiveData<List<UpcomingMovieEntity>> {
        return  dbMovie.upcomingMovieDao().getAllMovies()
    }

    override fun getUpcomingMovie(id: Int): LiveData<UpcomingMovieEntity> {
        return dbMovie.upcomingMovieDao().getMovie(id)
    }

    override suspend fun insertAllUpcomings(movie: List<UpcomingMovieEntity>) {
        return dbMovie.upcomingMovieDao().insertAll(movie)
    }

    override suspend fun insertUpcoming(movie: UpcomingMovieEntity) {
        return dbMovie.upcomingMovieDao().insert(movie)
    }

    //RatingMovie
    override fun getAllRatingMovies(): LiveData<List<RatingMovieEntity>> {
        return  dbMovie.ratingMovieDao().getAllMovies()
    }

    override fun getRatingMovie(id: Int): LiveData<RatingMovieEntity> {
        return dbMovie.ratingMovieDao().getMovie(id)
    }

    override suspend fun insertAllRatings(movie: List<RatingMovieEntity>) {
        return dbMovie.ratingMovieDao().insertAll(movie)
    }

    override suspend fun insertRating(movie: RatingMovieEntity) {
        return dbMovie.ratingMovieDao().insert(movie)
    }

}