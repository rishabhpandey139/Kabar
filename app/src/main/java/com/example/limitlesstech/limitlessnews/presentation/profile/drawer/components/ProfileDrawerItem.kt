package com.example.limitlesstech.limitlessnews.presentation.profile.drawer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ProfileDrawerItem(
    title: String,
    icon: ImageVector,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val contentColor =
        if (enabled) {
            MaterialTheme.colorScheme.onSurface
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.5f
            )
        }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(
                horizontal = 24.dp,
                vertical = 18.dp
            ),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = contentColor
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = contentColor,
            modifier = Modifier.weight(1f)
        )

        if (!enabled) {

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Locked",
                tint = contentColor
            )
        }
    }
}