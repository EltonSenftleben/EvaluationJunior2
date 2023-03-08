package com.example.evaluationjunior_2.utils.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluationjunior_2.App
import com.example.evaluationjunior_2.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen(
    title: Int = R.string.app_name,
    titleString: String = "",
    navController: NavController,
    iconBack: Boolean = false,
    showBottomBar: Boolean = true,
    content: @Composable () -> Unit = {},
) {

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (showBottomBar) {
            CustomBottomBar(
                navController = navController,
                destinations = App.destinations
            )
        }
    }, containerColor = MaterialTheme.colorScheme.background, content = {

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStartPercent = 50,
                            bottomEndPercent = 50
                        )
                    )
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (iconBack){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "",
                        modifier = Modifier.padding(start = 30.dp).clickable { navController.popBackStack() }
                    )
                }

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if(titleString == "")stringResource(id = title) else titleString,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }

    })

}