package com.ag.projects.shamsstorecompose.presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.utils.NavArguments

@Composable
fun ProductDetailsScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val id = backStackEntry.arguments?.getInt(NavArguments.PRODUCT_ID)
    Column(modifier = Modifier.fillMaxSize()) {

        id?.let {
            Text(text = "$it")

        }
    }

}