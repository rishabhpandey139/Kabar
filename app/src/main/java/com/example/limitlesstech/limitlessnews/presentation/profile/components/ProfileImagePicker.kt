package com.example.limitlesstech.limitlessnews.features.profile.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileImagePicker(
    imageUri: Uri?,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.BottomEnd
    ) {

        AsyncImage(
            model = imageUri,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { onImageClick() },
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Camera",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )

        }

    }
}