package com.ag.projects.shamsstorecompose.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.ProductCatalogCard
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val productsCatalog by viewModel.allProducts.collectAsState()
    val data = productsCatalog?.data?.find { it.type == "main_categories" }
    val content = data?.content

    var textState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CommonHeader(
            editTextValue = textState,
            onValueChange = {
                textState = it
            },
            screenName = "",
            onBackClick = {},
            changeLocation = "Change"
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        ) {
            content?.let {
                items(content) {
                    ProductCatalogCard(
                        imageUrl = it.image,
                        productName = it.name
                    )
                }
            }
        }
    }


}