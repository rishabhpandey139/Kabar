package com.example.limitlesstech.limitlessnews.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.R
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    // 🔥 Next destination
    val startDestination by
    viewModel.startDestination.collectAsState()

    // 🔥 Animation values
    val (scale, alpha) =
        rememberSplashAnimation()

    // 🔥 Navigation
    LaunchedEffect(startDestination) {

        // 🔥 Ignore splash route
        if (startDestination != Routes.Splash) {

            navController.navigate(startDestination) {

                popUpTo(Routes.Splash) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),

        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                R.drawable.kabar_logo
            ),

            contentDescription = "Logo",

            modifier = Modifier
                .size(180.dp) // original size
                .scale(scale) // zoom animation
                .alpha(alpha) // fade animation
        )
    }
}