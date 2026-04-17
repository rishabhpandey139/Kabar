// app/src/main/java/com/example/limitlesstech/limitlessnews/presentation/navigation/Navgraph.kt
package com.example.limitlesstech.limitlessnews.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.limitlesstech.limitlessnews.presentation.common.SelectionViewModel
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.DetailScreen
import com.example.limitlesstech.limitlessnews.presentation.home.HomeScreen
import com.example.limitlesstech.limitlessnews.presentation.home.HomeViewModel
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.CountryScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.SourceScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.TopicScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.UserSelection
    ) {
        // Parent graph \("UserSelection"\) \-\- no UI screen required.
        navigation<Routes.UserSelection>(
            startDestination = Routes.Country
        ) {
            composable<Routes.Country> {
                val parentEntry = remember(navController) {
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                CountryScreen(navController = navController, viewModel = vm)
            }

            composable<Routes.Topic> {
                val parentEntry = remember(navController) {
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                TopicScreen(navController = navController, viewModel = vm)
            }

            composable<Routes.Source> {
                val parentEntry = remember(navController) {
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                SourceScreen(navController = navController, viewModel = vm)
            }
        }

        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }

        composable<Routes.Details> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.Details>()
            val parentEntry = remember(navController) {
                navController.getBackStackEntry(Routes.Home)
            }
            val sharedHomeVm: HomeViewModel = hiltViewModel(parentEntry)

            DetailScreen(
                navController = navController,
                articleId = args.articleId,
                viewModel = sharedHomeVm
            )
        }
    }
}