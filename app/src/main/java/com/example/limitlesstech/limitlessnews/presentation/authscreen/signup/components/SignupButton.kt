package com.example.limitlesstech.limitlessnews.presentation.authscreen.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignupButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading, // ✅ disable when invalid or loading
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text("Sign Up")
        }
    }
}