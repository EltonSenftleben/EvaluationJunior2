package com.example.evaluationjunior_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.evaluationjunior_2.ui.popular.detail.PopularDetailScreen
import com.example.evaluationjunior_2.ui.upcoming.UpcomingScreen
import com.example.evaluationjunior_2.ui.popular.PopularScreen
import com.example.evaluationjunior_2.ui.rating.RatingScreen
import com.example.evaluationjunior_2.ui.rating.detail.RatingDetailContent
import com.example.evaluationjunior_2.ui.rating.detail.RatingDetailScreen
import com.example.evaluationjunior_2.ui.upcoming.detail.UpcomingDetailScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(navController = navController as NavHostController, startDestination = Screens.UPCOMING_SCREEN) {
        composable(Screens.UPCOMING_SCREEN){
            UpcomingScreen(navController = navController)
        }
        composable(Screens.POPULAR_SCREEN){
            PopularScreen(navController = navController)
        }
        composable(Screens.RATING_SCREEN){
            RatingScreen(navController = navController)
        }
        composable(Screens.POPULAR_DETAIL_SCREEN){
            PopularDetailScreen(navController = navController)
        }
        composable(Screens.UPCOMING_DETAIL_SCREEN){
            UpcomingDetailScreen(navController = navController)
        }
        composable(Screens.RATING_DETAIL_SCREEN){
            RatingDetailScreen(navController = navController)
        }
    }
}