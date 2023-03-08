package com.example.evaluationjunior_2.repository

import com.example.web.APIConstants
import com.example.web.ApiService
import com.example.web.BaseApiResponse
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: ApiService
): MovieRepository, BaseApiResponse() {

    override fun getPopular(key : String) = safeApiCall{
        apiService.getPopular(key)
    }

    override fun getUpcoming(key: String) = safeApiCall{
        apiService.getUpcoming(key)
    }

    override fun getTopRate(key: String) = safeApiCall{
        apiService.getTopRate(key)
    }

    override fun getMovie(id: Int) = safeApiCall {
        apiService.getMovie(id)
    }
}