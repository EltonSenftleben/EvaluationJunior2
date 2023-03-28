package com.example.evaluationjunior_2.ui.popular

import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.request.ImageRequest
import com.example.domain.entities.PopularMovieEntity
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
class PopularViewModel @Inject constructor(
    private val localMovieRepository: LocalMovieRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val movies = localMovieRepository.getAllPopularMovies()

    suspend fun getPopular(context: Context) = viewModelScope.launch(Dispatchers.Main) {
        movieRepository.getPopular(APIConstants.apiKey).collect {
            when (it) {
                is NetworkResult.Error -> {
                    _isLoading.postValue(false)
                }
                is NetworkResult.Loading -> {
                    _isLoading.postValue(true)
                }
                is NetworkResult.Success -> {
                    val movieList = it.data?.movieList ?: emptyList()
                    val movieListWithImages = mutableListOf<PopularMovieEntity>()
                    for (movie in movieList) {
                        val imageByteArray = try {
                            val bitmap = Coil.imageLoader(context).execute(
                                ImageRequest.Builder(context)
                                    .data(APIConstants.prefixImage + movie.imageUrl)
                                    .size(300, 400) // Tamaño máximo de la imagen
                                    .build(),
                            ).drawable?.toBitmap()
                            bitmap?.let { Convert.bitmapToByteArray(it) }
                        } catch (e: Exception) {
                            null
                        }
                        movieListWithImages.add(
                            PopularMovieEntity(
                                id = movie.id,
                                title = movie.title,
                                voteAverage = movie.voteAverage,
                                popularity = movie.popularity,
                                imageUrl = movie.imageUrl,
                                image = imageByteArray,
                                overview = movie.overview,
                            ),
                        )
                    }
                    localMovieRepository.insertAllPopulars(movieListWithImages)
                    _isLoading.postValue(false)
                }
            }
        }
    }
}
