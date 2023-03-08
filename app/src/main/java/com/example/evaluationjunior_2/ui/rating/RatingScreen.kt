package com.example.evaluationjunior_2.ui.rating

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.domain.entities.RatingMovieEntity
import com.example.evaluationjunior_2.App
import com.example.evaluationjunior_2.R
import com.example.evaluationjunior_2.ui.navigation.Params
import com.example.evaluationjunior_2.ui.navigation.Screens
import com.example.evaluationjunior_2.utils.Conecction
import com.example.evaluationjunior_2.utils.Convert.BytearrayToPainter
import com.example.evaluationjunior_2.utils.ui.LoadingContent
import com.example.evaluationjunior_2.utils.ui.Screen
import com.example.web.APIConstants
import kotlinx.coroutines.Dispatchers

@Composable
fun RatingScreen(navController: NavController, viewModel: RatingViewModel = hiltViewModel()){
    val context = LocalContext.current

    val isLoading by viewModel.isLoading.observeAsState(false)
    val moviesRatingList by viewModel.movies.observeAsState(emptyList())

    LaunchedEffect(viewModel) {
        viewModel.getRating(context)
    }

    Screen(navController = navController, title = R.string.bottom_bar_navigation_rating_title) {
        LoadingContent(isLoading = isLoading, modifier = Modifier.fillMaxSize()) {
            RatingContent(moviesRatingList, context, navController)
        }
    }
}

@Composable
fun RatingContent(moviesRatingList: List<RatingMovieEntity>, context: Context, navController: NavController){
    Log.e("TEST", "$moviesRatingList" )

    var list by remember { mutableStateOf(moviesRatingList)}

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 165.dp),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
    ) {
        items(list) { movie ->
            RatingImageLoaderList(movie = movie, context = context, navController = navController)
        }

        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun RatingImageLoaderList(movie: RatingMovieEntity, context: Context, navController: NavController) {

    if (!Conecction.isInternetAvailable(context)) {
        if (movie.image != null) {
            val localImage = rememberCoroutineScope().run {Dispatchers.IO
                mutableStateOf(BytearrayToPainter(movie.image!!))
            }
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(vertical = 10.dp)
                    .clickable {
                        Log.e("TEST", " click: $movie ")
                        App.navigation[Params.MOVIE] = movie
                        navController.navigate(Screens.RATING_DETAIL_SCREEN)
                    },
                painter = localImage.value,
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        }else{
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(470.dp)
                    .padding(vertical = 20.dp),
                painter = painterResource(id = R.drawable.img_no_image),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        }
    } else {
        Image(
            modifier = Modifier
                .size(250.dp)
                .padding(vertical = 10.dp)
                .clickable {
                    Log.e("TEST", " click: $movie ")
                    App.navigation[Params.MOVIE] = movie
                    navController.navigate(Screens.RATING_DETAIL_SCREEN)
                },
            painter = rememberAsyncImagePainter(model = ImageRequest.Builder(context)
                .data(data = APIConstants.prefixImage + movie.imageUrl)
                .apply {
                    error(R.drawable.img_no_image)
                    memoryCachePolicy(CachePolicy.DISABLED)
                    diskCachePolicy(CachePolicy.DISABLED)
                }
                .build()
            ),
            contentDescription = "",
            contentScale = ContentScale.Inside
        )
    }
}
