package com.example.limitlesstech.limitlessnews.data.repositoryImpl

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.limitlesstech.limitlessnews.domain.repository.NetworkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : NetworkRepository {

    override fun isInternetAvailable(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}