package com.ag.projects.shamsstorecompose.presentation.screen.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.presentation.ui.theme.RegisterBGGrey
import com.ag.projects.shamsstorecompose.utils.Constants
import com.ag.projects.shamsstorecompose.utils.NavArguments
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.Screen

@ExperimentalMaterial3Api
@Composable
fun RegisterUserNameScreen(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry
) {

    val userPhoneNumber = navBackStackEntry.arguments?.getString(NavArguments.USER_PHONE_NUMBER)
    val countryId = navBackStackEntry.arguments?.getInt(NavArguments.COUNTRY_ID)

    val countryCode = navBackStackEntry.arguments?.getString(NavArguments.COUNTRY_CODE)


    val viewModel: RegisterUserNameViewModel = hiltViewModel()
    val registerState by viewModel.register.collectAsState()

    var registerUserName by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {

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
                text = stringResource(id = R.string.hello),
                color = Color.Blue,
                fontSize = 37.sp
            )

            Text(
                text = stringResource(id = R.string.let_s_get_started),
                color = Color.Black,
                fontSize = 34.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.please_enter_your_name_to_create_your_new_account),
                color = Color.Black,
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(RegisterBGGrey)
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .padding(start = 12.dp, end = 12.dp)
        ) {

            Text(
                text = "+$countryCode$userPhoneNumber",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(14.dp),
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(text = stringResource(id = R.string.your_name))

            OutlinedTextField(
                value = registerUserName,
                onValueChange = { userName ->
                    registerUserName = userName
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                placeholder = {
                    Text(text = stringResource(id = R.string.your_name))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    viewModel.register(
                        AuthenticationRequest(
                            country_id = countryId,
                            phone = userPhoneNumber,
                            device_token = Constants.DEVICE_TOKEN,
                            full_name = registerUserName,
                        )
                    )
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
                    text = stringResource(id = R.string.confirm),
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.if_a_you_already_have_an_account_please_log_in),
                color = Color.Black,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))


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
                    text = stringResource(id = R.string.login),
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        when (registerState) {
            is Result.Error -> {}
            Result.Loading -> {}
            is Result.Success -> {
                val data = (registerState as Result.Success).data.data
                val userName = data.full_name
                val userPhone = data.phone_complete_form
                LaunchedEffect(key1 = registerState) {
                    navHostController.navigate(
                        Screen.LoginSuccess.rout + "/$userName/$userPhone"
                    )
                }
            }
        }
    }
}