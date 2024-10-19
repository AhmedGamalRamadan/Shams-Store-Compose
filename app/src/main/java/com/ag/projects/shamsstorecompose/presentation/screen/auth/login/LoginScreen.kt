package com.ag.projects.shamsstorecompose.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.domain.model.auth.login.AuthenticationRequest
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.spinner_country.CountryItem
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.presentation.ui.theme.RegisterBGGrey
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.Screen

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navHostController: NavHostController,
) {

    val viewModel: LoginScreenViewModel = hiltViewModel()
    var countryID = 0
    var countryCode = "000"
    var userPhoneNumber by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val countryList by viewModel.countries.collectAsState()
    val userLogin by viewModel.login.collectAsState()

    var checkboxState by remember {
        mutableStateOf(false)
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
                text = stringResource(id = R.string.log_in_to_your_account_by_your_mobile_number),
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
                modifier = Modifier
                    .padding(start = 14.dp),
                text = stringResource(id = R.string.mobile_number),
                color = Color.Black,
                fontSize = 14.sp
            )

            // Row for Country Code and user Phone Number
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .height(50.dp)
                    .background(Color.White)
            ) {

                when (countryList) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        val country = (countryList as Result.Success).data.data
                        LazyColumn {
                            item {
                                CountryItem(country = country[0])
                                countryID = country[0].id
                                countryCode = country[0].phone_code
                            }
                        }
                    }

                }

                OutlinedTextField(
                    value = userPhoneNumber,
                    onValueChange = { phoneNumber ->
                        userPhoneNumber = phoneNumber
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .focusable()
                        .background(White),
                    placeholder = {
                        Text(text = stringResource(id = R.string.mobile_number))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = White,
                        unfocusedBorderColor = White,
                        containerColor = White
                    )
                )
            }

            // Row for checkbox
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Checkbox(
                    checked = checkboxState,
                    onCheckedChange = {
                        checkboxState = it
                    }
                )
                Text(
                    text = stringResource(id = R.string.i_agree_to_the_processing_of_personal_data_that_i_confirm_with_terms_conditions),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    viewModel.login(
                        loginRequest = AuthenticationRequest(
                            country_id = countryID,
                            phone = userPhoneNumber,
                            device_token = "",
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightGreen
                )
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = White,
                    fontSize = 18.sp
                )
            }
        }

        when (userLogin) {
            is Result.Error -> {}
            Result.Loading -> {}
            is Result.Success -> {
                /*
            Navigate to OTP Screen with
            1_ user mobile number(countryCode + limit)
            2_ userID
            3_CountryCode
            */
                LaunchedEffect(key1 = userLogin) {
                    navHostController.navigate(
                        "${Screen.VerifyOTP.rout}/${countryID}/${countryCode}/${userPhoneNumber}"
                    )
                }
            }
        }
    }
}