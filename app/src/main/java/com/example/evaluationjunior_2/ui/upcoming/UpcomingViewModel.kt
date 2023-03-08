package com.example.evaluationjunior_2.ui.upcoming

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.request.ImageRequest
import com.example.domain.entities.UpcomingMovieEntity
import com.example.domain.models.Movie
import com.example.domain.models.MovieList
import com.example.evaluationjunior_2.repository.LocalMovieRepository
import com.example.evaluationjunior_2.repository.MovieRepository
import com.example.evaluationjunior_2.utils.Convert
import com.example.web.APIConstants
import com.example.web.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val localMovieRepository: LocalMovieRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val movies = localMovieRepository.getAllUpcomingMovies()

    suspend fun getUpcoming(context: Context) = viewModelScope.launch(Dispatchers.Main) {
        movieRepository.getUpcoming(APIConstants.apiKey).collect {
            when (it) {
                is NetworkResult.Error -> {
                    _isLoading.postValue(false)
                }
                is NetworkResult.Loading -> {
                    _isLoading.postValue(true)
                }
                is NetworkResult.Success -> {

                    val movieList = it.data?.movieList ?: emptyList()
                    val movieListWithImages = mutableListOf<UpcomingMovieEntity>()
                    for (movie in movieList) {
                        val imageByteArray = try {
                            val bitmap = Coil.imageLoader(context).execute(
                                ImageRequest.Builder(context)
                                    .data(APIConstants.prefixImage + movie.imageUrl)
                                    .size(300, 400) // Tamaño máximo de la imagen
                                    .build()
                            ).drawable?.toBitmap()
                            bitmap?.let { Convert.bitmapToByteArray(it) }
                        } catch (e: Exception) {
                            null
                        }
                        movieListWithImages.add(
                            UpcomingMovieEntity(
                                id = movie.id,
                                title = movie.title,
                                voteAverage = movie.voteAverage,
                                popularity = movie.popularity,
                                imageUrl = movie.imageUrl,
                                image = imageByteArray,
                                overview = movie.overview
                            )
                        )
                    }
                    localMovieRepository.insertAllUpcomings(movieListWithImages)
                    _isLoading.postValue(false)
                }
            }
        }
    }

}