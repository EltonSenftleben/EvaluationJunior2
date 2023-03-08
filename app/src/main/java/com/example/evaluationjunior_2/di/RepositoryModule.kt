package com.example.evaluationjunior_2.di

import com.example.evaluationjunior_2.repository.LocalMovieRepository
import com.example.examen.repository.LocalMovieRepositoryImp
import com.example.evaluationjunior_2.repository.MovieRepository
import com.example.evaluationjunior_2.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocalMovieRepository(localMovieRepositoryImp: LocalMovieRepositoryImp) : LocalMovieRepository

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImp: MovieRepositoryImp) : MovieRepository
}