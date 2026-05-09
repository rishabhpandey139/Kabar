package com.example.limitlesstech.limitlessnews.presentation.authscreen.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components.LoginButton
import com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components.LoginHeader
import com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components.LoginTextField
import com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components.SocialButtons
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // 🔥 Success navigation
    LaunchedEffect(state.isSuccess) {

        if (state.isSuccess) {

            snackbarHostState.showSnackbar(
                "Login Successful"
            )

            delay(1000)

            navController.navigate(Routes.UserSelection) {

                popUpTo(Routes.Login) {
                    inclusive = true
                }
            }
        }
    }

    // 🔥 API error snackbar
    LaunchedEffect(state.error) {

        state.error?.let { error ->

            val message = when (error) {

                is DomainError.InvalidCredentials ->
                    "Wrong email or password"

                is DomainError.Network ->
                    "Check internet connection"

                is DomainError.PasswordMissingDigit -> "Add 1 number"
                is DomainError.PasswordMissingUpper -> "Add 1 uppercase letter"

                else ->
                    "Something went wrong"
            }

            snackbarHostState.showSnackbar(message)

            // 🔥 reset error
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            LoginHeader()

            // 🔥 Email field
            LoginTextField(
                label = "Username",
                value = state.username,
                onValueChange = viewModel::onUsernameChange,
                error = state.usernameError
            )

            // 🔥 Password field
            LoginTextField(
                label = "Password",
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                error = state.passwordError,
                isPassword = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = state.rememberMe,
                        onCheckedChange = {
                            viewModel.toggleRemember()
                        }
                    )

                    Text("Remember me")
                }

                Text(
                    text = "Forgot password?",
                    color = Color(0xFF2979FF),

                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Forgot)
                    }
                )
            }

            // 🔥 Login button
            LoginButton(
                onClick = {
                    viewModel.login()
                },

                enabled = state.isFormValid,
                isLoading = state.isLoading
            )

            Text(
                text = "or continue with",

                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )

            SocialButtons()

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.Center
            ) {

                Text("Don't have an account? ")

                Text(
                    text = "Sign Up",

                    color = Color(0xFF2979FF),

                    modifier = Modifier.clickable {
                        navController.navigate(Routes.SignUp)
                    }
                )
            }
        }
    }
}