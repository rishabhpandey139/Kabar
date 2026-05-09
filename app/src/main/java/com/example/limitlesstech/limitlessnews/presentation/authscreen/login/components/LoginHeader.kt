package com.example.limitlesstech.limitlessnews.presentation.authscreen.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginHeader() {
    Column {
        Text(
            text = "Hello",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Again!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2979FF)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Welcome back you've\nbeen missed",
            color = Color.Gray
        )
    }
}