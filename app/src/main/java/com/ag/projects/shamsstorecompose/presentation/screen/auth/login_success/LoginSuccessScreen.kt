package com.ag.projects.shamsstorecompose.presentation.screen.auth.login_success

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.navigation.NavigationItem
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.utils.NavArguments

@Composable
fun LoginSuccessScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val userName = backStackEntry.arguments?.getString(NavArguments.USER_NAME)
    val userPhoneNumber = backStackEntry.arguments?.getString(NavArguments.FULL_PHONE_NUMBER)


    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(35.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(
                id = R.string.back
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.wellcome),
                color = Color.Blue,
                fontSize = 37.sp
            )

            Text(
                text = "$userName",
                color = Color.Blue,
                fontSize = 34.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "+$userPhoneNumber",
                color = Color.Black,
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        Button(
            onClick = {
                navHostController.navigate(NavigationItem.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
                .height(48.dp),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightGreen
            )
        ) {
            Text(
                text = stringResource(id = R.string.start_shopping),
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
                .height(48.dp),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            border = BorderStroke(width = 1.dp, color = Color.Black)
        ) {
            Text(
                text = stringResource(id = R.string.my_account),
                color = Color.Blue,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}