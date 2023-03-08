package com.example.evaluationjunior_2.utils.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.evaluationjunior_2.R

@Composable
fun StaticRatingBar(rating: Float, maxRating: Int = 5) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(maxRating) { index ->
            val ratingIcon =
                if (index < rating/2) R.drawable.ic_star_filled else R.drawable.ic_star_outlined
            Icon(
                painter = painterResource(id = ratingIcon),
                contentDescription = null,
                //tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}