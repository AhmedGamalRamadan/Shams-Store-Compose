package com.ag.projects.shamsstorecompose.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkConnection{
    fun checkWifiConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.let { manager ->
            val network = manager.activeNetwork
            val capabilities = manager.getNetworkCapabilities(network)
            return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
        }
        return false
    }
}
