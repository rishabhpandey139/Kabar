package com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ForgotButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {

        Button(
            onClick = onClick,

            enabled = enabled && !isLoading,//Form valid ho aur loading na ho tabhi button chalega

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Submit")
            }
        }
    }
}