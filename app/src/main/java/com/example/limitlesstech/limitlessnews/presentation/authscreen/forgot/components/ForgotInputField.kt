package com.example.limitlesstech.limitlessnews.presentation.authscreen.forgot.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ForgotInputField(
    value: String,
    onValueChange: (String) -> Unit,
    error: String? = null
) {

    Column {

        OutlinedTextField(
            value = value,

            onValueChange = onValueChange,

            modifier = Modifier.fillMaxWidth(),

            placeholder = {
                Text("Email")
            },

            singleLine = true,

            isError = error != null
        )

        // 🔥 Error text
        if (error != null) {

            Text(
                text = error,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}