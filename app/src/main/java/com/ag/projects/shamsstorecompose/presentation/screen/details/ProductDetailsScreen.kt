package com.ag.projects.shamsstorecompose.presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.utils.NavArguments

@Composable
fun ProductDetailsScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val viewModel: ProductDetailsScreenViewModel = hiltViewModel()
    val id = backStackEntry.arguments?.getInt(NavArguments.PRODUCT_ID) ?: 0
    id?.let {
        viewModel.getProductDetails(it)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        id?.let {
            Text(text = "$it")

        }
    }

}