package com.example.limitlesstech.limitlessnews.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.launch

@Composable
fun rememberSplashAnimation(): Pair<Float, Float> {

    // 🔥 Start with small size
    val scale = remember {
        Animatable(0.6f)
    }

    // 🔥 Start invisible
    val alpha = remember {
        Animatable(0f)
    }

    // 🔥 Start animation
    LaunchedEffect(Unit) {

        // ✅ Zoom animation
        launch {

            scale.animateTo(
                targetValue = 1f,

                animationSpec = tween(
                    durationMillis = 1200,
                    easing = FastOutSlowInEasing
                )
            )
        }

        // ✅ Fade animation
        launch {

            alpha.animateTo(
                targetValue = 1f,

                animationSpec = tween(
                    durationMillis = 1200
                )
            )
        }
    }


    return Pair(scale.value, alpha.value)
}