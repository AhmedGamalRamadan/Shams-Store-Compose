package com.ag.projects.shamsstorecompose.presentation.screen.auth.verify_otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.otp.OtpInputField
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.presentation.ui.theme.RegisterBGGrey

@Composable
fun VerifyOTPScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {

    val countryID = backStackEntry.arguments?.getString("countryID")
    val countryCode = backStackEntry.arguments?.getString("countryCode")
    val userPhoneNumber = backStackEntry.arguments?.getString("userPhoneNumber")

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp
                ),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(
                    id = R.string.back
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.phone_verification),
                color = Color.Blue,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.you_will_receive_a_sms_code_to_your_phone_number),
                color = Color.Black,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = "+$countryCode$userPhoneNumber",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(RegisterBGGrey)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 2.dp))
                .padding(start = 18.dp, end = 18.dp)
        ) {

            Text(
                text = stringResource(id = R.string.enter_received_code),
                modifier = Modifier.padding(8.dp)
            )

            OtpInputField(
                itemCount = 4,
                onOtpComplete = {},
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 22.dp, end = 22.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightGreen
                )
            ) {
                Text(
                    text = stringResource(id = R.string.verify),
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.resend_code),
                color = Color.Blue,
                fontSize = 14.sp
            )

        }

    }
}