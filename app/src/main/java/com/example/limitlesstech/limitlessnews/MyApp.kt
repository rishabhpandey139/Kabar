package com.example.limitlesstech.limitlessnews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Application = app ka starting point, yahi se Hilt pura app control karta hai
@HiltAndroidApp
class MyApp : Application() {
}