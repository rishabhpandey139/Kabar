package com.example.limitlesstech.limitlessnews.presentation.authscreen.signup


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.domain.common.DomainError
import com.example.limitlesstech.limitlessnews.presentation.authscreen.signup.components.*
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }


    var passwordVisible by remember { mutableStateOf(false) }

    // 🔥 Navigation
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            snackbarHostState.showSnackbar("Signup Successful")
            delay(1000)
            navController.navigate(Routes.Login) {
                popUpTo(Routes.SignUp) { inclusive = true }
            }
        }
    }

    // 🔥 Show Snackbar on error
    LaunchedEffect(state.error) {
        state.error?.let { error ->
            val message = when (error) {
                is DomainError.EmptyEmail -> "Email required"
                is DomainError.EmptyPassword -> "Password required"
                is DomainError.InvalidEmailFormat -> "Invalid email"
                is DomainError.PasswordTooShort -> "Password too short"
                is DomainError.PasswordMissingUpper -> "Add 1 uppercase letter"
                is DomainError.PasswordMissingDigit -> "Add 1 number"
                is DomainError.UserAlreadyExists ->
                    "Email already in use"

                is DomainError.Network-> "Check your connection"
                else -> "Something went wrong"
            }

            snackbarHostState.showSnackbar(message)
            // 🔥 reset error
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(Modifier.height(10.dp))

            SignupHeader()

            SignupTextField(
                label = "Enter Email-id",
                value = state.username,
                onValueChange = viewModel::onUsernameChange,
                error=state.usernameError
            )

            SignupTextField(
                label = "Password",
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                error=state.passwordError,
                isPassword = true,
                passwordVisible = passwordVisible,
                onToggle = { passwordVisible = !passwordVisible }
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.rememberMe,
                    onCheckedChange = { viewModel.toggleRemember() }
                )
                Text("Remember me")
            }

            SignupButton(
                onClick = { viewModel.signup() },
                enabled = state.isFormValid,
                isLoading = state.isLoading
            )

            Text(
                "or continue with",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            SocialRow()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account ? ")
                Text(
                    text = "Login",
                    color = Color(0xFF2979FF),
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Login)
                    }
                )
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}