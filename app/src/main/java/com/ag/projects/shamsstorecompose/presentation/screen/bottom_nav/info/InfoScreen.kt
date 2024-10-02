package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader

@Composable
fun InfoScreen(
    navHostController: NavHostController

) {

    var textSearchState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        CommonHeader(
            editTextValue = textSearchState,
            onValueChange = {
                textSearchState = it
            },
            screenName = stringResource(id = R.string.info),
            onBackClick = {
                navHostController.navigateUp()
            },
            iconBack = painterResource(id = R.drawable.ic_arrow_back)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {


        }
    }
}