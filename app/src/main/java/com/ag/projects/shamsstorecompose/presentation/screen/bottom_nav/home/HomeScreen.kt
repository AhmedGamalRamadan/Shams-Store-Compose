package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
import com.ag.projects.domain.model.products.cart.AddToCartRequest
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.products.BrandsItemCard
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.ProductCatalogCard
import com.ag.projects.shamsstorecompose.presentation.components.products.ProductItemCard
import com.ag.projects.shamsstorecompose.presentation.components.products.slider.ViewPagerSliderItem
import com.ag.projects.shamsstorecompose.presentation.navigation.NavigationItem
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Blue
import com.ag.projects.shamsstorecompose.utils.Constants
import com.ag.projects.shamsstorecompose.utils.network.NetworkConnection
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    address: String
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val allProductsState by viewModel.allProducts.collectAsState()
    val context = LocalContext.current

    val sharedPrefManager = SharedPreferencesManager(context)

    var textSearchState by remember {
        mutableStateOf("")
    }
    val isWifiConnected = NetworkConnection.checkWifiConnection(context)

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
            screenName = "",
            onBackClick = {},
            changeLocation = stringResource(id = R.string.change),
            address = address
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            if (!isWifiConnected) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_no_internt),
                        contentDescription = stringResource(R.string.no_internet_connection)
                    )
                }
            } else {
                when (allProductsState) {
                    is Result.Error -> {}
                    Result.Loading -> {}

                    is Result.Success -> {
                        val data = (allProductsState as Result.Success).data.data

                        //First Slider
                        val firstSlider =
                            data.find { it.type == Constants.FIRST_BANNER_SLIDERS }?.content?.map { it.image }
                        firstSlider?.let {
                            ViewPagerSliderItem(imagesUrls = it)
                        }

                        //Products Catalog
                        val productsCatalogContent =
                            data.find { it.type == Constants.PRODUCT_CATALOG }?.content
                        productsCatalogContent?.let {
                            Spacer(modifier = Modifier.height(5.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.product_catalog),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = stringResource(id = R.string.view_all),
                                    color = Blue,
                                    modifier = Modifier.clickable {
                                        navHostController.navigate(NavigationItem.AllCategory.route)
                                    }
                                )
                            }
                            LazyHorizontalGrid(
                                rows = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                items(productsCatalogContent) {
                                    ProductCatalogCard(
                                        imageUrl = it.image,
                                        productName = it.name
                                    )
                                }
                            }
                        }

                        //Middle Slider
                        val middleSliderImages =
                            data.find { it.type == Constants.MIDDLE_BANNER_SLIDER }?.content?.map { it.image }
                        middleSliderImages?.let {
                            Spacer(modifier = Modifier.height(5.dp))
                            ViewPagerSliderItem(imagesUrls = it)
                        }

                        //Brand Products
                        val brandsContent = data.find { it.type == Constants.BRAND }?.content
                        brandsContent?.let {
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.brand),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = stringResource(id = R.string.view_all),
                                    color = Blue,
                                    modifier = Modifier.clickable {
                                        navHostController.navigate(NavigationItem.Brand.route)
                                    }
                                )
                            }
                            LazyHorizontalGrid(
                                rows = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                items(brandsContent) {
                                    BrandsItemCard(
                                        brandImage = it.image,
                                    )
                                }
                            }
                        }

                        //Most Popular Products
                        val popularProductContent =
                            data.find { it.type == Constants.MOST_POPULAR_PRODUCTS }?.content

                        popularProductContent?.let {
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.most_popular_products),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = stringResource(id = R.string.view_all),
                                    color = Blue,
                                    modifier = Modifier.clickable {
                                        navHostController.navigate(NavigationItem.Brand.route)
                                    }
                                )
                            }

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            ) {
                                items(popularProductContent) {
                                    ProductItemCard(
                                        content = it,
                                        navHostController = navHostController,
                                        addToCart = { productId, productQuantity ->

                                            viewModel.addToCart(
                                                bearerToken = "Bearer ${sharedPrefManager.getToken()}",
                                                guestToken = "1221",
                                                addToCartRequest = AddToCartRequest(
                                                    product_id = productId,
                                                    quantity = productQuantity
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}