package com.ag.projects.shamsstorecompose.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ag.projects.shamsstorecompose.presentation.common.BottomNavigationBar
import com.ag.projects.shamsstorecompose.presentation.screen.brands.BrandsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.cart.CartScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.category.CategoryScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.home.HomeScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info.InfoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Home.route
            ) {
                composable(NavigationItem.Home.route) {
                    HomeScreen(navHostController = navController)
                }
                composable(NavigationItem.AllCategory.route) {
                    CategoryScreen(navHostController = navController)
                }
                composable(NavigationItem.Cart.route) {
                    CartScreen()
                }
                composable(NavigationItem.Info.route) {
                    InfoScreen()
                }

                composable(NavigationItem.Brand.route) {
                    BrandsScreen(navHostController = navController)
                }
            }
        }
    }
}

