package com.ag.projects.shamsstorecompose.presentation.screen.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
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

    LaunchedEffect(key1 = Unit){
        viewModel.getAddresses(
            auth=""
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
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_no_location),
                            contentDescription = stringResource(id = R.string.no_data_available)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 14.dp, end = 14.dp)
                    ) {
                        items(userAddresses) {
                            Text(text = it.country.toString())
                        }
                    }
                }
            }
        }


    }
}