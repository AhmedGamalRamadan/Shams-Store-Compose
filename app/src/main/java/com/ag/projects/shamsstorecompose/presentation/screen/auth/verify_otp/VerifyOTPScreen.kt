package com.ag.projects.shamsstorecompose.presentation.screen.auth.verify_otp

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.otp.OtpInputField
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.presentation.ui.theme.RegisterBGGrey
import com.ag.projects.shamsstorecompose.utils.Constants
import com.ag.projects.shamsstorecompose.utils.NavArguments
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.Screen

@Composable
fun VerifyOTPScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry,
) {
    /*
    => Receive 3 arguments from login screen
     1_ userMobileNumber(without country code)
     2_ countryId
     3_ countryCode
      */
    val countryID = backStackEntry.arguments?.getInt(NavArguments.COUNTRY_ID)
    val countryCode = backStackEntry.arguments?.getString(NavArguments.COUNTRY_CODE)
    val userPhoneNumber = backStackEntry.arguments?.getString(NavArguments.USER_PHONE_NUMBER)

    val viewModel: VerifyOTPScreenViewModel = hiltViewModel()

    val verifyOTPState by viewModel.verifyOTPState.collectAsState()

    var otpCode by remember{
        mutableStateOf("")
    }

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
                onOtpComplete = {
                    otpCode = it
                },
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 22.dp, end = 22.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {

                    if (otpCode.length < 4) {

                    } else {
                        viewModel.verifyOTP(
                            loginRequest = AuthenticationRequest(
                                country_id = countryID,
                                phone = userPhoneNumber,
                                device_token = Constants.DEVICE_TOKEN,
                                code = otpCode,
                            )
                        )
                    }

                    when (verifyOTPState) {
                        is Result.Error -> {}
                        Result.Loading -> {}
                        is Result.Success -> {
                            val data = (verifyOTPState as Result.Success).data.data

                            Log.d("the status is ","Success")
                            // if register_complete_step == 2 navigate to login Successful
                            //with userName & Full Phone Number
                            if (data.register_complete_step == 2) {
                                val fullPhoneNumber = data.phone_complete_form
                                val userFullName = data.full_name
                                navHostController.navigate(
                                    Screen.LoginSuccess.rout + "/$userFullName/$fullPhoneNumber"
                                )
                            } else {
                                // navigate to register user name screen write user name
                                // and pass phone without country code
                                // and complete phone number
                                navHostController.navigate(
                                    Screen.RegisterUserName.rout + "/${data.phone}/${data.country.id}"
                                )
                            }
                        }
                    }

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