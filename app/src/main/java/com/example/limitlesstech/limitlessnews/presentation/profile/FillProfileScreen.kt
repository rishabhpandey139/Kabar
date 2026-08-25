package com.example.limitlesstech.limitlessnews.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.NextButton
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.ProfileImagePicker
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.ProfileTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillProfileScreen(
    isEditMode: Boolean = false,
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(isEditMode) {

        if (isEditMode) {
            viewModel.onEvent(
                ProfileEvent.LoadProfile
            )
        }
    }

    LaunchedEffect(uiState.isSuccess) {

        if (uiState.isSuccess) {
            onNextClick()
        }
    }

    LaunchedEffect(uiState.errorMessage) {

        uiState.errorMessage?.let {

            snackbarHostState.showSnackbar(it)

            viewModel.onEvent(
                ProfileEvent.ClearErrorMessage
            )
        }
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = if (isEditMode) {
                            "Edit Profile"
                        } else {
                            "Fill Your Profile"
                        }
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors =
                    TopAppBarDefaults
                        .centerAlignedTopAppBarColors()
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Profile Image
            ProfileImagePicker(
                imageUri = uiState.imageUri,

                // Existing Cloudinary image for Edit Profile
                existingImageUrl = uiState.existingImageUrl,

                onImageSelected = {
                    viewModel.onEvent(
                        ProfileEvent.ImageChanged(it)
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            ProfileTextField(
                value = uiState.username,
                onValueChange = {
                    viewModel.onEvent(
                        ProfileEvent.UsernameChanged(it)
                    )
                },
                label = "Username",
                placeholder = "Enter username"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            ProfileTextField(
                value = uiState.fullName,
                onValueChange = {
                    viewModel.onEvent(
                        ProfileEvent.FullNameChanged(it)
                    )
                },
                label = "Full Name",
                placeholder = "Enter full name"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            ProfileTextField(
                value = uiState.email,
                onValueChange = {
                    viewModel.onEvent(
                        ProfileEvent.EmailChanged(it)
                    )
                },
                label = "Email Address",
                placeholder = "example@gmail.com",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            ProfileTextField(
                value = uiState.phone,
                onValueChange = {
                    viewModel.onEvent(
                        ProfileEvent.PhoneChanged(it)
                    )
                },
                label = "Phone Number",
                placeholder = "+91 9876543210",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            NextButton(

                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp),

                isLoading = uiState.isLoading,

                onClick = {

                    viewModel.onEvent(

                        if (isEditMode) {
                            ProfileEvent.UpdateProfile
                        } else {
                            ProfileEvent.SaveProfile
                        }
                    )
                }
            )
        }
    }
}