package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.dao.PopularMovieDao
import com.example.database.dao.RatingMovieDao
import com.example.database.dao.UpcomingMovieDao
import com.example.domain.entities.PopularMovieEntity
import com.example.domain.entities.RatingMovieEntity
import com.example.domain.entities.UpcomingMovieEntity

@Database(entities = [PopularMovieEntity::class, UpcomingMovieEntity::class, RatingMovieEntity::class], version = 1)
abstract class DBMovie: RoomDatabase() {

    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun upcomingMovieDao(): UpcomingMovieDao
    abstract fun ratingMovieDao(): RatingMovieDao
    companion object{
        @JvmStatic
        fun newInstance(context: Context):DBMovie{
            return Room.databaseBuilder(context,DBMovie::class.java,"DbMovie")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}