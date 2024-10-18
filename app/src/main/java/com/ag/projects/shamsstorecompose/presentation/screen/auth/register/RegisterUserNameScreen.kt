package com.ag.projects.shamsstorecompose.presentation.screen.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun RegisterUserNameScreen(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry
) {

    val userPhoneNumber =navBackStackEntry.arguments?.getString("userPhoneNumber")
    val countryId =navBackStackEntry.arguments?.getInt("countryID")


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "$countryId...$userPhoneNumber")
    }
}