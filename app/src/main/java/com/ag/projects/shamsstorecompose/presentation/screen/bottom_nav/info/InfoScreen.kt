package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.qa.CustomRowQA
import com.ag.projects.shamsstorecompose.presentation.navigation.NavigationItem
import com.ag.projects.shamsstorecompose.utils.Screen

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
            iconBack = painterResource(id = R.drawable.ic_arrow_back),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            Text(
                text = stringResource(id = R.string.profile),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            CustomRowQA(
                title = stringResource(id = R.string.saved_address),
                onRowClick = {
                    navHostController.navigate("")
                }
            )

            CustomRowQA(
                title = stringResource(id = R.string.which_list),
                onRowClick = {
                    navHostController.navigate("")
                }
            )

            CustomRowQA(
                title = stringResource(id = R.string.orders),
                onRowClick = {
                    navHostController.navigate("")
                }
            )

            CustomRowQA(
                title = stringResource(id = R.string.notifications),
                onRowClick = {
                    navHostController.navigate("")
                }
            )

            CustomRowQA(
                title = stringResource(id = R.string.payment_methods),
                onRowClick = {
                    navHostController.navigate("")
                }
            )
            CustomRowQA(
                title = stringResource(id = R.string.settings),
                onRowClick = {
                    navHostController.navigate(NavigationItem.Settings.route)
                }
            )
            Text(
                text = stringResource(id = R.string.help),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            CustomRowQA(
                title = stringResource(id = R.string.store_locator),
                onRowClick = {
                    navHostController.navigate("")
                }
            )
            CustomRowQA(
                title = stringResource(id = R.string.faq),
                onRowClick = {
                    navHostController.navigate("")
                }
            )
            CustomRowQA(
                title = stringResource(id = R.string.contact_us),
                onRowClick = {
                    navHostController.navigate("")
                }
            )
            Text(
                text = stringResource(id = R.string.legal),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            CustomRowQA(
                title = stringResource(id = R.string.privacy_policy),
                onRowClick = {
                    navHostController.navigate(Screen.PrivacyPolicy.rout)
                }
            )
            CustomRowQA(
                title = stringResource(id = R.string.terms_conditions),
                onRowClick = {
                    navHostController.navigate("")
                }
            )
        }
    }
}