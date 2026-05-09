package com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components.ForgotButton
import com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components.ForgotHeader
import com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components.ForgotInputField
import com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components.ForgotTopBar
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun ForgotScreen(
    navController: NavController,
    viewModel: ForgotViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // 🔥 Success
    LaunchedEffect(state.isSuccess) {

        if (state.isSuccess) {

            snackbarHostState.showSnackbar(
                "If this email exists, reset link has been sent"
            )

            delay(1000)

            navController.navigate(Routes.Login) {

                popUpTo(Routes.Forgot) {
                    inclusive = true
                }
            }
        }
    }

    // 🔥 API error
    LaunchedEffect(state.error) {

        state.error?.let { error ->

            val message = when (error) {

                is DomainError.Network ->
                    "Check internet connection"


                else ->
                    "Something went wrong"
            }

            snackbarHostState.showSnackbar(message)

            viewModel.clearError()
        }
    }

    Scaffold(

        topBar = {
            ForgotTopBar(
                onBack = {
                    navController.popBackStack()
                }
            )
        },

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ForgotHeader()

            ForgotInputField(
                value = state.email,

                onValueChange = viewModel::onEmailChange,

                error = state.emailError
            )

            ForgotButton(
                onClick = {
                    viewModel.sendReset()
                },

                enabled = state.isFormValid,

                isLoading = state.isLoading
            )

            Text(
                text = "Back to Login",

                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(Routes.Login)
                    }
            )
        }
    }
}