package com.example.evaluationjunior_2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.evaluationjunior_2.ui.theme.EvaluationJunior2Theme
import androidx.navigation.compose.rememberNavController
import com.example.evaluationjunior_2.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvaluationJunior2Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier,) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
