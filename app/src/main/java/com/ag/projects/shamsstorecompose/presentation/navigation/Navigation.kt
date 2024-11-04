package com.ag.projects.shamsstorecompose.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ag.projects.shamsstorecompose.presentation.common.BottomNavigationBar
import com.ag.projects.shamsstorecompose.presentation.screen.address.AddressScreen
import com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands.AllCategoriesBrandsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.auth.login.LoginScreen
import com.ag.projects.shamsstorecompose.presentation.screen.auth.login_success.LoginSuccessScreen
import com.ag.projects.shamsstorecompose.presentation.screen.auth.register.RegisterUserNameScreen
import com.ag.projects.shamsstorecompose.presentation.screen.auth.verify_otp.VerifyOTPScreen
import com.ag.projects.shamsstorecompose.presentation.screen.brands.BrandsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.cart.CartScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.category.CategoryScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.home.HomeScreen
import com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info.InfoScreen
import com.ag.projects.shamsstorecompose.presentation.screen.details.ProductDetailsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.faq.FAQScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.privacy_policy.PrivacyPolicyScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.settings.SettingsScreen
import com.ag.projects.shamsstorecompose.presentation.screen.qa.terms_condition.TermsAndConditionScreen
import com.ag.projects.shamsstorecompose.utils.NavArguments
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
                /*
                Auth
                 */
                composable(Screen.Login.rout) {
                    LoginScreen(navHostController = navController)
                }

                composable(
                    Screen.VerifyOTP.rout + "/{${NavArguments.COUNTRY_ID}}/{${NavArguments.COUNTRY_CODE}}/{${NavArguments.USER_PHONE_NUMBER}}",
                    arguments = listOf(
                        navArgument(NavArguments.COUNTRY_ID) { type = NavType.IntType },
                        navArgument(NavArguments.COUNTRY_CODE) { type = NavType.StringType },
                        navArgument(NavArguments.USER_PHONE_NUMBER) { type = NavType.StringType },
                    )
                ) { navBackStackEntry ->
                    VerifyOTPScreen(
                        navHostController = navController,
                        backStackEntry = navBackStackEntry
                    )
                }

                composable(
                    Screen.RegisterUserName.rout + "/{${NavArguments.USER_PHONE_NUMBER}}/{${NavArguments.COUNTRY_ID}}/{${NavArguments.COUNTRY_CODE}}",
                    arguments = listOf(
                        navArgument(NavArguments.USER_PHONE_NUMBER) { type = NavType.StringType },
                        navArgument(NavArguments.COUNTRY_ID) { type = NavType.IntType },
                        navArgument(NavArguments.COUNTRY_CODE) { type = NavType.StringType },
                    )
                ) { navBackStackEntry ->
                    RegisterUserNameScreen(
                        navHostController = navController,
                        navBackStackEntry = navBackStackEntry
                    )
                }

                composable(
                    Screen.LoginSuccess.rout + "/{${NavArguments.USER_NAME}}/{${NavArguments.FULL_PHONE_NUMBER}}",
                    arguments = listOf(
                        navArgument(NavArguments.USER_NAME) { type = NavType.StringType },
                        navArgument(NavArguments.FULL_PHONE_NUMBER) { type = NavType.StringType }
                    )
                ) { navBackStackEntry ->
                    LoginSuccessScreen(
                        navHostController = navController,
                        backStackEntry = navBackStackEntry
                    )
                }

                composable(Screen.Details.rout + "/{${NavArguments.PRODUCT_ID}}",
                    arguments = listOf(
                        navArgument(NavArguments.PRODUCT_ID) { type = NavType.IntType }
                    )
                ) { navBackStackEntry ->
                    ProductDetailsScreen(
                        navHostController = navController,
                        backStackEntry = navBackStackEntry
                    )
                }

                composable(Screen.Address.rout){
                    AddressScreen(navHostController = navController)
                }

                composable(Screen.AllCategoriesBrands.rout){
                    AllCategoriesBrandsScreen()
                }
            }
        }
    }
}