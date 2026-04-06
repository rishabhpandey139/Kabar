
package com.example.limitlesstech.limitlessnews.presentation.navigation




import android.annotation.SuppressLint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.DetailScreen

import com.example.limitlesstech.limitlessnews.presentation.home.HomeScreen
import com.example.limitlesstech.limitlessnews.presentation.home.NewsViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable<Routes.Details> { backstackEntry ->
            val args = backstackEntry.toRoute<Routes.Details>()
            //SharedViewModel can be used to pass the article data instead of fetching it again using the articleId
            val parentEntry = remember(navController){
               navController.getBackStackEntry(Routes.Home)
            }
            val sharedViewModel: NewsViewModel = hiltViewModel(parentEntry)
            DetailScreen(articleId = args.articleId,
                viewModel   = sharedViewModel)
        }
    }
}