package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.slider.ShoppingCartItem
import com.ag.projects.shamsstorecompose.utils.Result

@Composable
fun CartScreen(
    navHostController: NavHostController

) {

    var textSearchState by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val sharedPrefManager = SharedPreferencesManager(context)

    val viewModel: CartScreenViewModel = hiltViewModel()
    val carts by viewModel.getCartItems.collectAsState()

    LaunchedEffect(key1 = carts) {
        viewModel.getCarts(
            bearerToken = "Bearer ${sharedPrefManager.getToken()}",
            addressId = 1,
            isPicked = 2,
            branchWorkTimeId = 1
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CommonHeader(
            editTextValue = textSearchState,
            onValueChange = {
                textSearchState = it
            },
            screenName = stringResource(id = R.string.shoppingcart),
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


            when (carts) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {

                    (carts as Result.Success).data.data.cart?.let { cart ->

                        LazyColumn {
                            items(cart.items) { productItem ->

                                ShoppingCartItem(
                                    productItem = productItem
                                )
                            }
                        }
                    } ?: run {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.shopping_cart_is_empty),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 33.sp
                            )
                        }
                    }

                }
            }

        }
    }
}