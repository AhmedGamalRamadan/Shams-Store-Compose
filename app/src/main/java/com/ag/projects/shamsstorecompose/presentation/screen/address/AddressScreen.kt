package com.ag.projects.shamsstorecompose.presentation.screen.address

import android.view.animation.LayoutAnimationController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader

@Composable
fun AddressScreen(
    navHostController: NavHostController,
) {

    var textSearchState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CommonHeader(
            modifier = Modifier.fillMaxWidth(),
            editTextValue = textSearchState,
            onValueChange = {
                textSearchState = it
            },
            screenName = "",
            onBackClick = {
                navHostController.navigateUp()
            },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 14.dp)
        ) {


        }
    }
}