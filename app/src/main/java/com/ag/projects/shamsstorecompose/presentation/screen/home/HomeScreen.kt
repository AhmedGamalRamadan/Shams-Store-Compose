package com.ag.projects.shamsstorecompose.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.products.BrandsItemCard
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.ProductCatalogCard
import com.ag.projects.shamsstorecompose.presentation.components.products.ViewPagerSliderItem
import com.ag.projects.shamsstorecompose.presentation.navigation.NavigationItem
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Blue
import com.ag.projects.shamsstorecompose.utils.Constants
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val allProductsState by viewModel.allProducts.collectAsState()

    val dataProductsFirstSliders =
        allProductsState?.data?.find { it.type == Constants.FIRST_BANNER_SLIDERS }
    val dataProductsCatalog = allProductsState?.data?.find { it.type == Constants.PRODUCT_CATALOG }
    val dataProductsBrands = allProductsState?.data?.find { it.type == Constants.BRAND }
    val dataMiddleSlider =
        allProductsState?.data?.find { it.type == Constants.MIDDLE_BANNER_SLIDER }

    val middleSliderImages = dataMiddleSlider?.content?.map { it.image }
    
    val firstSliderImages = dataProductsFirstSliders?.content?.map { it.image }
    val productsCatalogContent = dataProductsCatalog?.content
    val brandsContent = dataProductsBrands?.content


    var textSearchState by remember {
        mutableStateOf("")
    }

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
            changeLocation = "Change"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            //First Slider
            firstSliderImages?.let {
                ViewPagerSliderItem(imagesUrls = it)
            }

            //Products Catalog
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
            middleSliderImages?.let {
                Spacer(modifier = Modifier.height(5.dp))
                ViewPagerSliderItem(imagesUrls = it)
            }

            //Brand Products
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

        }
    }

}