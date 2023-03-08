package com.example.evaluationjunior_2.ui.upcoming.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.UpcomingMovieEntity
import com.example.evaluationjunior_2.App
import com.example.evaluationjunior_2.repository.MovieRepository
import com.example.evaluationjunior_2.ui.navigation.Params
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _movie = MutableLiveData(App.navigation[Params.MOVIE] as UpcomingMovieEntity)
    val movie: LiveData<UpcomingMovieEntity> = _movie

    fun setIsLoading (it: Boolean){
        _isLoading.value = it
    }

}