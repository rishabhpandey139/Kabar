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
import com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.ForgotScreen
import com.example.limitlesstech.limitlessnews.presentation.authscreen.login.LoginScreen
import com.example.limitlesstech.limitlessnews.presentation.authscreen.signup.SignupScreen
import com.example.limitlesstech.limitlessnews.presentation.common.SelectionViewModel
import com.example.limitlesstech.limitlessnews.presentation.detailScreen.DetailScreen
import com.example.limitlesstech.limitlessnews.presentation.home.HomeScreen
import com.example.limitlesstech.limitlessnews.presentation.home.HomeViewModel

import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.CountryScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.SourceScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.TopicScreen


//NavGraph navigation handle karta hai aur shared ViewModel ka scope decide karne me help karta hai.
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.SignUp
    ) {
        // 🔥 SignUp SCREEN
        composable<Routes.SignUp> {
            SignupScreen(
                navController = navController


            )
        }

        // 🔥 LOGIN SCREEN
        composable<Routes.Login> {
            LoginScreen(
                navController = navController
            )
        }
        // 🔥 forgot SCREEN
        composable<Routes.Forgot> {
            ForgotScreen(
                navController = navController
            )
        }



        // 🔥 USER SELECTION FLOW
        navigation<Routes.UserSelection>(
            startDestination = Routes.Country
        ) {

            composable<Routes.Country> {
                val parentEntry = remember{
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                CountryScreen(navController, vm)
            }

            composable<Routes.Topic> {
                val parentEntry = remember(navController) {
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                TopicScreen(navController, vm)
            }

            composable<Routes.Source> {
                val parentEntry = remember(navController) {
                    navController.getBackStackEntry(Routes.UserSelection)
                }
                val vm: SelectionViewModel = hiltViewModel(parentEntry)
                SourceScreen(navController, vm)
            }
        }

        // 🔥 HOME
        composable<Routes.Home> {

            HomeScreen(navController = navController)
        }
        // 🔥 DETAILS
        composable<Routes.Details> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.Details>()

            val parentEntry = remember{
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