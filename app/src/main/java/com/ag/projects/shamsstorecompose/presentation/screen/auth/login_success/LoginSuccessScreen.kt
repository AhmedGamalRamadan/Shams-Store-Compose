package com.ag.projects.shamsstorecompose.presentation.screen.auth.login_success

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun LoginSuccessScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val userName = backStackEntry.arguments?.getString("userName")
    val userPhoneNumber = backStackEntry.arguments?.getString("fullPhoneNumber")


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "$userName...$userPhoneNumber")
    }
}