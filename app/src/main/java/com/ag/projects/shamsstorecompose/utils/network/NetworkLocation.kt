package com.ag.projects.shamsstorecompose.utils.network

import android.content.Context
import android.location.Geocoder
import android.location.Address
import java.io.IOException
import java.util.Locale

fun getAddressFromLatLng(context: Context,lat: Double, lng: Double): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    try {
        val addresses: List<Address>? = geocoder.getFromLocation(lat, lng, 1)
        if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]
            val city = address.locality
            val country = address.countryName
            return "$city , $country"
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return "Address not found"
}