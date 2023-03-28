package com.example.evaluationjunior_2

import android.app.Application
import com.example.evaluationjunior_2.ui.navigation.BottomNavigationDestination
import com.example.evaluationjunior_2.ui.navigation.Screens
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object{
        val destinations = listOf(
            BottomNavigationDestination(
                icon = R.drawable.ic_upcoming_movie,
                title = R.string.bottom_bar_navigation_upcoming_title,
                route = Screens.UPCOMING_SCREEN
            ),
            BottomNavigationDestination(
                icon = R.drawable.ic_popular_movie,
                title = R.string.bottom_bar_navigation_popular_title,
                route = Screens.POPULAR_SCREEN
            ),
            BottomNavigationDestination(
                icon = R.drawable.ic_rating_movie,
                title = R.string.bottom_bar_navigation_rating_title,
                route = Screens.RATING_SCREEN
            )
        )

        val navigation: MutableMap<String, Any> = mutableMapOf()
    }
}
