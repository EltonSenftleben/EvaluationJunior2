package com.example.evaluationjunior_2.ui.popular.detail

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.domain.entities.PopularMovieEntity
import com.example.evaluationjunior_2.R
import com.example.evaluationjunior_2.utils.Conecction
import com.example.evaluationjunior_2.utils.Convert.BytearrayToPainter
import com.example.evaluationjunior_2.utils.ui.LoadingContent
import com.example.evaluationjunior_2.utils.ui.Screen
import com.example.evaluationjunior_2.utils.ui.StaticRatingBar
import com.example.web.APIConstants

@Composable
fun PopularDetailScreen(
    navController: NavController,
    viewModel: PopularDetailViewModel = hiltViewModel()
) {
    val owner = LocalLifecycleOwner.current
    val context = LocalContext.current

    var movie by remember { mutableStateOf<PopularMovieEntity?>(null) }
    viewModel.movie.observe(owner) { movie = it }

    var isLoading by remember { mutableStateOf(false) }
    viewModel.isLoading.observe(owner) { isLoading = it }

    Screen(
        titleString = movie?.title ?: "",
        navController = navController,
        iconBack = true,
        showBottomBar = false
    ) {
        LoadingContent(isLoading = isLoading, modifier = Modifier.fillMaxSize()) {
            if (movie != null) {
                PopularDetailContent(movie, context, viewModel)
                viewModel.setIsLoading(false)
            }
        }
    }
}

@Composable
fun PopularDetailContent(movie: PopularMovieEntity?, context: Context, viewModel: PopularDetailViewModel) {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ) {
        if (movie != null) {
            PopularImageLoaderDetail(movie = movie, context = context)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.movie_detail_overview),
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(text = movie?.overview ?: "", textAlign = TextAlign.Justify)

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.movie_detail_popularity),
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(text = movie?.popularity.toString())
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.movie_detail_vote),
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    //Text(text = movie?.voteAverage.toString())
                    movie?.voteAverage?.toFloat()?.let { StaticRatingBar(rating = it) }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }


}

@Composable
fun PopularImageLoaderDetail(movie: PopularMovieEntity, context: Context) {
    if (!Conecction.isInternetAvailable(context)) {
        if (movie?.image != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(470.dp)
                    .padding(vertical = 20.dp),
                painter = BytearrayToPainter(movie.image!!),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        } else {
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
                .fillMaxWidth()
                .size(470.dp)
                .padding(vertical = 20.dp),
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

