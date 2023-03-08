package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.RatingMovieEntity
import com.example.domain.models.Movie

@Dao
interface RatingMovieDao {

    @Query("SELECT * FROM RatingMovie")
    fun getAllMovies() : LiveData<List<RatingMovieEntity>>

    @Query("SELECT * FROM RatingMovie WHERE id = :id")
    fun getMovie(id: Int): LiveData<RatingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<RatingMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: RatingMovieEntity)
}