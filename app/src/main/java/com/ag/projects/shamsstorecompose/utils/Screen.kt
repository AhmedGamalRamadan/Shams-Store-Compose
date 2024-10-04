package com.ag.projects.shamsstorecompose.utils


sealed class Screen(val rout: String) {
    object Brand : Screen("brand")
    object Settings : Screen("settings")
    object ContactUS : Screen("contactUS")
    object FAQ : Screen("faq")
    object PrivacyPolicy : Screen("privacyPolicy")
    object TermsAndCondition : Screen("termsAndCondition")
}