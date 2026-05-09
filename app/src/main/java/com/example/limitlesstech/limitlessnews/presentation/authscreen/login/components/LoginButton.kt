package com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean
) {

    Button(
        onClick = onClick,

        enabled = enabled && !isLoading,

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text("Login")
        }
    }
}