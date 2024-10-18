package com.ag.projects.shamsstorecompose.presentation.screen.auth.verify_otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
@Composable
fun VerifyOTPScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val countryID =backStackEntry.arguments?.getString("countryID")
    val countryCode =backStackEntry.arguments?.getString("countryCode")
    val userPhoneNumber =backStackEntry.arguments?.getString("userPhoneNumber")

    Column(modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "$countryID//$countryCode//$userPhoneNumber")
    }
}