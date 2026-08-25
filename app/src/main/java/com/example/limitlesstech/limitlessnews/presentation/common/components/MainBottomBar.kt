package com.example.limitlesstech.limitlessnews.presentation.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes

@Composable
fun MainBottomBar(
    selectedRoute: Routes,
    navController: NavHostController,
    onProfileClick: () -> Unit = {}
) {

    val items = listOf(

        BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Routes.Home
        ),

        BottomNavItem(
            title = "Bookmark",
            icon = Icons.Default.Bookmark,
            route = Routes.Bookmark
        ),

        BottomNavItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = null
        )
    )

    NavigationBar {

        items.forEach { item ->

            NavigationBarItem(

                selected = item.route == selectedRoute,

                onClick = {

                    if (item.title == "Profile") {

                        onProfileClick()

                    } else if (
                        item.route != null &&
                        item.route != selectedRoute
                    ) {

                        navController.navigate(item.route) {

                            popUpTo(
                                navController
                                    .graph
                                    .findStartDestination()
                                    .id
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true

                            restoreState = true
                        }
                    }
                },

                icon = {

                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },

                label = {
                    Text(item.title)
                }
            )
        }
    }
}