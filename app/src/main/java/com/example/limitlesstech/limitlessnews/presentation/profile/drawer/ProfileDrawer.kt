package com.example.limitlesstech.limitlessnews.presentation.profile.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.limitlesstech.limitlessnews.presentation.profile.components.ProfileDrawerHeader
import com.example.limitlesstech.limitlessnews.presentation.profile.drawer.components.LogoutConfirmationDialog
import com.example.limitlesstech.limitlessnews.presentation.profile.drawer.components.ProfileDrawerItem


@Composable
fun ProfileDrawer(
    isDrawerOpen: Boolean,
    onBackClick: () -> Unit,
    onFillProfileClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onLogoutSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileDrawerViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    // Re-check profile every time drawer opens
    LaunchedEffect(isDrawerOpen) {

        if (isDrawerOpen) {
            viewModel.checkProfile()
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        ProfileDrawerHeader(
            onBackClick = onBackClick
        )

        Divider()

        if (uiState.isLoading) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()
            }

        } else {

            ProfileDrawerItem(
                title = "Fill Profile",
                icon = Icons.Default.PersonAdd,
                enabled = !uiState.isProfileCompleted,
                onClick = onFillProfileClick
            )

            ProfileDrawerItem(
                title = "Edit Profile",
                icon = Icons.Default.Edit,
                enabled = uiState.isProfileCompleted,
                onClick = onEditProfileClick
            )

            Divider(
                modifier = Modifier.padding(
                    vertical = 8.dp
                )
            )

            ProfileDrawerItem(
                title = "Logout",
                icon = Icons.Default.Logout,
                enabled = uiState.isProfileCompleted,
                onClick = {
                    showLogoutDialog = true
                }
            )
        }
    }

    if (showLogoutDialog) {

        LogoutConfirmationDialog(

            onConfirm = {

                showLogoutDialog = false

                viewModel.logout(
                    onSuccess = onLogoutSuccess
                )
            },

            onDismiss = {
                showLogoutDialog = false
            }
        )
    }
}