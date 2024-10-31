package com.ag.projects.shamsstorecompose.presentation.screen.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.address.AddressCard
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Green
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.utils.Result

@Composable
fun AddressScreen(
    navHostController: NavHostController,
) {

    val viewModel: AddressScreenViewModel = hiltViewModel()
    val addresses by viewModel.getAddresses.collectAsState()

    var textSearchState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val sharedPrefManager = SharedPreferencesManager(context)

    LaunchedEffect(key1 = Unit) {
        viewModel.getAddresses(
            auth = "Bearer ${sharedPrefManager.getToken()}"
        )
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
        when (addresses) {
            is Result.Error -> {}
            Result.Loading -> {}
            is Result.Success -> {
                val userAddresses = (addresses as Result.Success).data.data
                if (userAddresses.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_no_location),
                            contentDescription = stringResource(id = R.string.no_data_available)
                        )
                        Text(
                            text = stringResource(id = R.string.there_is_not_any_addresses_yet),
                            textAlign = TextAlign.Center,
                            color = Grey,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            text = stringResource(id = R.string.you_do_not_add_address),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 14.dp, end = 14.dp)
                        ) {
                            items(userAddresses) { defaultAddress ->
                                AddressCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    defaultAddress = defaultAddress,
                                    onDeleteClick = {},
                                    onEditClick = {},
                                    isDefault = defaultAddress.is_default!!,
                                    onDefaultChange = {}
                                )
                            }
                        }

                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(start = 14.dp, end = 14.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = LightGreen
                            ),
                        ) {
                            Text(
                                text = stringResource(id = R.string.add_new_address),
                                color = Color.White
                            )
                        }
                    }

                }
            }
        }


    }
}