package com.ag.projects.shamsstorecompose.presentation.navigation

import com.ag.projects.shamsstorecompose.R

sealed class NavigationItem(val route: String, val icon: Int, val label: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object AllCategory : NavigationItem("allCategory", R.drawable.ic_apps, "Category")
    object Cart : NavigationItem("cart", R.drawable.ic_cart, "Cart")
    object Info : NavigationItem("info", R.drawable.ic_profile, "Info")
    object Brand : NavigationItem("Brand", 0, "Brand")
}