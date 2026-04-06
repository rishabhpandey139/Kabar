package com.example.limitlesstech.limitlessnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import com.example.limitlesstech.limitlessnews.presentation.home.HomeScreen
import com.example.limitlesstech.limitlessnews.presentation.navigation.AppNavGraph
import com.example.limitlesstech.limitlessnews.ui.theme.LimitlessNewsTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LimitlessNewsTheme {
                    val navController = rememberNavController()
                        AppNavGraph(navController = navController)
                    }
                }
            }
        }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}