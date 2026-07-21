package com.example.limitlesstech.limitlessnews.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.NextButton
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.ProfileImagePicker
import com.example.limitlesstech.limitlessnews.features.profile.presentation.components.ProfileTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillProfileScreen(
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {

    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Fill Your Profile")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            ProfileImagePicker(
                imageUri = null,
                onImageClick = {
                    // TODO Open Gallery
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ProfileTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                placeholder = "Enter username"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full Name",
                placeholder = "Enter full name"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "example@gmail.com",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone Number",
                placeholder = "+91 9876543210",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp),
                onClick = onNextClick
            )
        }
    }
}