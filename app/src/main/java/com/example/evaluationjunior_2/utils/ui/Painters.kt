package com.example.evaluationjunior_2.utils.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.evaluationjunior_2.R

object Painters {
    @Composable
    fun networkImage(url: String? = "https://picsum.photos/300/200") = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url)
            .apply(block = fun ImageRequest.Builder.() {
                error(R.drawable.img_no_image)
                    .memoryCachePolicy(
                        CachePolicy.DISABLED
                    )
                    .diskCachePolicy(CachePolicy.DISABLED)
            }).build()
    )

    @Composable
    fun assetImage(id: Int = R.drawable.img_no_image) = painterResource(id = id)
}