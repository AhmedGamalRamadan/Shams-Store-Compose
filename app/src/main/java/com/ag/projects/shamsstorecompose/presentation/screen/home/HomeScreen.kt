package com.ag.projects.shamsstorecompose.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader

@Composable
fun HomeScreen() {

    var textState by remember {
        mutableStateOf("")
    }

    CommonHeader(
        editTextValue = textState,
        onValueChange = {
            textState = it
        },
        screenName = "Home",
        onBackClick = {},
        changeLocation = "Change")


}