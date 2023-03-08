package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.UpcomingMovieEntity
import com.example.domain.models.Movie

@Dao
interface UpcomingMovieDao {

    @Query("SELECT * FROM UpcomingMovie")
    fun getAllMovies() : LiveData<List<UpcomingMovieEntity>>

    @Query("SELECT * FROM UpcomingMovie WHERE id = :id")
    fun getMovie(id: Int): LiveData<UpcomingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<UpcomingMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: UpcomingMovieEntity)
}