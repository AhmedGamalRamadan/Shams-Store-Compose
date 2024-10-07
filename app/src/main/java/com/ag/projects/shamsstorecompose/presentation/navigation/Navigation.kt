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
import com.ag.projects.shamsstorecompose.presentation.screen.qa.faq.FAQScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.privacy_policy.PrivacyPolicyScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.settings.SettingsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.terms_condition.TermsAndConditionScreen
import com.ag.projects.shamsstorecompose.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    address: String
) {

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
                    HomeScreen(
                        navHostController = navController,
                        address = address
                    )
                }
                composable(NavigationItem.AllCategory.route) {
                    CategoryScreen(navHostController = navController)
                }
                composable(NavigationItem.Cart.route) {
                    CartScreen(navHostController = navController)
                }
                composable(NavigationItem.Info.route) {
                    InfoScreen(navHostController = navController)
                }

                composable(Screen.Brand.rout) {
                    BrandsScreen(navHostController = navController)
                }

                composable(Screen.Settings.rout) {
                    SettingsScreen(navHostController = navController)
                }

                composable(Screen.PrivacyPolicy.rout) {
                    PrivacyPolicyScreen(navHostController = navController)
                }

                composable(Screen.FAQ.rout) {
                    FAQScreen(navHostController = navController)
                }

                composable(Screen.TermsAndCondition.rout) {
                    TermsAndConditionScreen(navHostController = navController)
                }
            }
        }
    }
}

