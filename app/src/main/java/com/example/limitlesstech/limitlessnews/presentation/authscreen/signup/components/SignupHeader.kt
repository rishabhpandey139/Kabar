package com.example.limitlesstech.limitlessnews.presentation.authscreen.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SignupHeader() {
    Column {
        Text(
            "Hello!",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF2979FF)
        )
        Text(
            "Signup to get Started",
            color = Color.Gray
        )
    }
}