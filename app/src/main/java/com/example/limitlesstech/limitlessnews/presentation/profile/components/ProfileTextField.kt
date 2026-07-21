package com.example.limitlesstech.limitlessnews.features.profile.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    isError: Boolean = false
) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Start
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors()
    )
}