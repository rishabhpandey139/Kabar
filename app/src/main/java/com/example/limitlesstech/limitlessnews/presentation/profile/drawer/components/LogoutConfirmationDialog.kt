package com.example.limitlesstech.limitlessnews.presentation.profile.drawer.components
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text(
                text = "Logout?"
            )
        },

        text = {
            Text(
                text = "Your profile and user data will be removed from this device. Do you want to continue?"
            )
        },

        confirmButton = {

            TextButton(
                onClick = onConfirm
            ) {
                Text("Logout")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}