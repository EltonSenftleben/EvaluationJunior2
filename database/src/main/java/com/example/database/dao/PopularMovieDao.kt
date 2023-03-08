package com.example.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.PopularMovieEntity
import com.example.domain.models.Movie

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM PopularMovie")
    fun getAllMovies() : LiveData<List<PopularMovieEntity>>

    @Query("SELECT * FROM PopularMovie WHERE id = :id")
    fun getMovie(id: Int): LiveData<PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: PopularMovieEntity)
}