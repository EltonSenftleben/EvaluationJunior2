package com.example.evaluationjunior_2.utils

import android.content.Context
import android.util.Base64
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.evaluationjunior_2.dataStore
import kotlinx.coroutines.flow.map

object UserPreferences {
    val TITLE = "TITLE"
    val OVERVIEW = "OVERVIEW"
    val VOTE_AVERAGE = "VOTE_AVERAGE"
    val POPULARITY = "POPULARITY"
    val IMAGE_URL = "IMAGE_URL"
    val IMAGE = "IMAGE"

    suspend fun setMovie(context: Context, title: String, overview: String, voteAverage: Double, popularity: Double, imageUrl: String, image: ByteArray) {
        val encodedString = Base64.encodeToString(image, Base64.DEFAULT)
        context.dataStore.edit { data ->
            data[stringPreferencesKey(TITLE)] = title
            data[stringPreferencesKey(OVERVIEW)] = overview
            data[doublePreferencesKey(VOTE_AVERAGE)] = voteAverage
            data[doublePreferencesKey(POPULARITY)] = popularity
            data[stringPreferencesKey(IMAGE_URL)] = imageUrl
            data[stringPreferencesKey(IMAGE)] = encodedString
        }
    }

    fun getTitle(context: Context) =
        context.dataStore.data.map { data ->
            data[stringPreferencesKey(TITLE)] ?: ""
        }

    fun getOverview(context: Context) =
        context.dataStore.data.map { data ->
            data[stringPreferencesKey(OVERVIEW)] ?: ""
        }

    fun getVoteAverage(context: Context) =
        context.dataStore.data.map { data ->
            data[doublePreferencesKey(VOTE_AVERAGE)] ?: 0.0
        }

    fun getPopularity(context: Context) =
        context.dataStore.data.map { data ->
            data[doublePreferencesKey(POPULARITY)] ?: 0.0
        }

    fun getImageUrl(context: Context) =
        context.dataStore.data.map { data ->
            data[stringPreferencesKey(IMAGE_URL)] ?: ""
        }

    fun getImage(context: Context) =
        context.dataStore.data.map { data ->
            Base64.decode(data[stringPreferencesKey(IMAGE)], Base64.DEFAULT)
        }


}