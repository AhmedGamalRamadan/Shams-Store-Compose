package com.ag.projects.shamsstorecompose.presentation.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.slider.ViewPagerSliderItem
import com.ag.projects.shamsstorecompose.presentation.ui.theme.DarkBlue
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGreen
import com.ag.projects.shamsstorecompose.utils.Constants
import com.ag.projects.shamsstorecompose.utils.NavArguments
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    val id = backStackEntry.arguments?.getInt(NavArguments.PRODUCT_ID) ?: 0


    val viewModel: ProductDetailsScreenViewModel = hiltViewModel()
    val productDetails by viewModel.productDetailsState.collectAsState()

    id.let {
        viewModel.getProductDetails(it)
    }
    var textSearchState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 14.dp)
        ) {

            when (productDetails) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    val data = (productDetails as Result.Success).data.data

                    val productDetailsContent =
                        data.find { it.type == Constants.PRODUCT_DETAILS }?.content
                    val relatedProductsContent =
                        data.find { it.type == Constants.RELATED_PRODUCTS }?.content
                    val productMayULikeContent =
                        data.find { it.type == Constants.PRODUCTS_MAY_U_LIKE }?.content

                    //Product Details
                    productDetailsContent?.let { content ->

                        //View Pager For Product Images
                        ViewPagerSliderItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            imagesUrls = content.map { it.image }
                        )

                        Spacer(modifier = Modifier.height(7.dp))

                        for (product in productDetailsContent) {

                            //Brand
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                Text(
                                    text = stringResource(id = R.string.brand),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = product.brand.name,
                                    color = DarkBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(7.dp))

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = product.name,
                            )

                            Spacer(modifier = Modifier.height(7.dp))

                            //Price
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                product.price_after?.let {
                                    Text(
                                        text = "${product.price} ${product.currency}",
                                        color = LightGreen,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(6.dp),
                                        text = product.price_after.toString(),
                                        color = Grey
                                    )
                                } ?: Text(
                                    text = "${product.price} ${product.currency}",
                                    color = LightGreen,
                                    fontSize = 20.sp
                                )

                            }

                            Spacer(modifier = Modifier.height(7.dp))

                            //Review
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_star),
                                    contentDescription = stringResource(id = R.string.review)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    text = product.rate_avg.toString(),
                                    color = Grey
                                )
                            }

                            Spacer(modifier = Modifier.height(7.dp))

                            product.desc?.let {
                                Text(
                                    text = stringResource(id = R.string.product_description),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(modifier = Modifier.height(7.dp))
                                Text(
                                    text = product.desc,
                                    color = Grey
                                )
                            }
                        }
                    }


                }
            }
        }

    }

}