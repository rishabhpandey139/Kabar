package com.example.limitlesstech.limitlessnews.presentation.common.components

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

data class BottomNavItem(

    val title: String,

    val icon: ImageVector,

    val route: Routes
)