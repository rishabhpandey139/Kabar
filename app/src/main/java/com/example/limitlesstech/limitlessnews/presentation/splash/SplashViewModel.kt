package com.example.limitlesstech.limitlessnews.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStoreManager
) : ViewModel() {


    private val _startDestination =
        MutableStateFlow<Routes>(Routes.Splash)

    val startDestination: StateFlow<Routes> =
        _startDestination

    init {
        startSplash()
    }

    private fun startSplash() {

        viewModelScope.launch {

            // 🔥 Splash delay
            delay(2000)

            // 🔥 Read DataStore values
            val isLoggedIn =
                dataStore.isLoggedIn.first()

            val isOnboardingDone =
                dataStore.isOnboardingDone.first()

            // 🔥 Decide next screen
            _startDestination.value = when {

                isLoggedIn ->
                    Routes.Home

                isOnboardingDone ->
                    Routes.Login

                else ->
                    Routes.Onboarding
            }
        }
    }
}