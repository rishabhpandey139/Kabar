package com.example.limitlesstech.limitlessnews.presentation.detailScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomActions() {

    Row(
        modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding(),


        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Row {
            Text("❤️ 24.5K")
            Spacer(Modifier.width(16.dp))
            Text("💬 1K")
        }

        Text("🔖")
    }
}