package com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SocialButtons() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text("Facebook")
        }

        Button(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text("Google")
        }
    }
}