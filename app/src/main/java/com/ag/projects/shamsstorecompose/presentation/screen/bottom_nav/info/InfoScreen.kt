package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
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
    val context = LocalContext.current

    val sharedPrefManager = SharedPreferencesManager(context)
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

            if (sharedPrefManager.isLoggedIn()) {
                //Show user Profile

            } else {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp, end = 14.dp)
                        .height(88.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(id = R.string.create_account_or_login))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.profile),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            CustomRowQA(
                title = stringResource(id = R.string.saved_address),
                onRowClick = {}
            )

            CustomRowQA(
                title = stringResource(id = R.string.which_list),
                onRowClick = {}
            )

            CustomRowQA(
                title = stringResource(id = R.string.orders),
                onRowClick = {}
            )

            CustomRowQA(
                title = stringResource(id = R.string.notifications),
                onRowClick = {}
            )

            CustomRowQA(
                title = stringResource(id = R.string.payment_methods),
                onRowClick = {}
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
                onRowClick = {}
            )
            CustomRowQA(
                title = stringResource(id = R.string.faq),
                onRowClick = {
                    navHostController.navigate(Screen.FAQ.rout)
                }
            )
            CustomRowQA(
                title = stringResource(id = R.string.contact_us),
                onRowClick = {}
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
                    navHostController.navigate(Screen.TermsAndCondition.rout)
                }
            )
        }
    }
}