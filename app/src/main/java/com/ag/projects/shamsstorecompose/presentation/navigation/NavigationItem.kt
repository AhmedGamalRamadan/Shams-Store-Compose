package com.ag.projects.shamsstorecompose.presentation.navigation

import com.ag.projects.shamsstorecompose.R

sealed class NavigationItem(val route: String, val icon: Int, val label: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Brands : NavigationItem("brands", R.drawable.ic_apps, "Brands")
    object Cart : NavigationItem("cart", R.drawable.ic_cart, "Cart")
    object Info : NavigationItem("info", R.drawable.ic_profile, "Info")
}