package com.example.evaluationjunior_2.ui.rating

import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.request.ImageRequest
import com.example.domain.entities.RatingMovieEntity
import com.example.domain.models.Movie
import com.example.evaluationjunior_2.repository.LocalMovieRepository
import com.example.evaluationjunior_2.repository.MovieRepository
import com.example.evaluationjunior_2.utils.Convert
import com.example.web.APIConstants
import com.example.web.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val localMovieRepository: LocalMovieRepository
): ViewModel(){
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val movies = localMovieRepository.getAllRatingMovies()

    suspend fun getRating(context: Context) = viewModelScope.launch(Dispatchers.Main) {
        movieRepository.getTopRate(APIConstants.apiKey).collect {
            when (it) {
                is NetworkResult.Error -> {
                    _isLoading.postValue(false)
                }
                is NetworkResult.Loading -> {
                    _isLoading.postValue(true)
                }
                is NetworkResult.Success -> {

                    val movieList = it.data?.movieList ?: emptyList()
                    val movieListWithImages = mutableListOf<RatingMovieEntity>()
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
                            RatingMovieEntity(
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
                    localMovieRepository.insertAllRatings(movieListWithImages)
                    _isLoading.postValue(false)
                }
            }
        }
    }
}