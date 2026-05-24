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
import com.example.limitlesstech.limitlessnews.presentation.onboarding.OnboardingScreen
import com.example.limitlesstech.limitlessnews.presentation.splash.SplashScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.CountryScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.SourceScreen
import com.example.limitlesstech.limitlessnews.presentation.userSelectionScreens.components.TopicScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {

        // 🔥 Splash
        composable<Routes.Splash> {

            SplashScreen(
                navController = navController
            )
        }

        // 🔥 Onboarding
        composable<Routes.Onboarding> {

            OnboardingScreen(
                navController = navController
            )
        }

        // 🔥 Signup
        composable<Routes.SignUp> {

            SignupScreen(
                navController = navController
            )
        }

        // 🔥 Login
        composable<Routes.Login> {

            LoginScreen(
                navController = navController
            )
        }

        // 🔥 Forgot Password
        composable<Routes.Forgot> {

            ForgotScreen(
                navController = navController
            )
        }

        // 🔥 USER SELECTION GRAPH
        navigation<Routes.UserSelection>(
            startDestination = Routes.Country
        ) {

            // 🔥 Country
            composable<Routes.Country> {

                val parentEntry = remember(navController) {

                    navController.getBackStackEntry(
                        Routes.UserSelection
                    )
                }

                val vm: SelectionViewModel =
                    hiltViewModel(parentEntry)

                CountryScreen(
                    navController = navController,
                    viewModel = vm
                )
            }

            // 🔥 Topic
            composable<Routes.Topic> {

                val parentEntry = remember(navController) {

                    navController.getBackStackEntry(
                        Routes.UserSelection
                    )
                }

                val vm: SelectionViewModel =
                    hiltViewModel(parentEntry)

                TopicScreen(
                    navController = navController,
                    viewModel = vm
                )
            }

            // 🔥 Source
            composable<Routes.Source> {

                val parentEntry = remember(navController) {

                    navController.getBackStackEntry(
                        Routes.UserSelection
                    )
                }

                val vm: SelectionViewModel =
                    hiltViewModel(parentEntry)

                SourceScreen(
                    navController = navController,
                    viewModel = vm
                )
            }
        }

        // 🔥 Home
        composable<Routes.Home> {

            HomeScreen(
                navController = navController
            )
        }

        // 🔥 Details
        composable<Routes.Details> { backStackEntry ->

            val args =
                backStackEntry.toRoute<Routes.Details>()

            // 🔥 Shared HomeViewModel
            val parentEntry = remember(navController) {

                navController.getBackStackEntry(
                    Routes.Home
                )
            }

            val homeViewModel: HomeViewModel =
                hiltViewModel(parentEntry)

            DetailScreen(
                navController = navController,
                articleId = args.articleId,
                viewModel = homeViewModel
            )
        }
    }
}