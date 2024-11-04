package com.ag.projects.shamsstorecompose.presentation.screen.brands

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.CategoriesItem
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel
import com.ag.projects.shamsstorecompose.utils.Result

@Composable
fun BrandsScreen(
    navHostController: NavHostController
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val brandContent by viewModel.brandsState.collectAsState()

    var textSearchState by remember {
        mutableStateOf("")
    }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    val numberOfColumns = when {
        screenWidthDp >= 600.dp -> 4 // Large screens
        screenWidthDp >= 400.dp -> 3 // Medium screens
        else -> 2 // Small screens
    }

    viewModel.getAllBrands()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CommonHeader(
            editTextValue = textSearchState,
            onValueChange = {
                textSearchState = it
            },
            screenName = stringResource(id = R.string.all_brands),
            onBackClick = {
                navHostController.navigateUp()
            },
            iconBack = painterResource(id = R.drawable.ic_arrow_back),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            when (brandContent) {
                is Result.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (brandContent as Result.Error).message)
                    }

                }

                Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.loading))
                    }
                }

                is Result.Success -> {
                    val productsBrands = (brandContent as Result.Success).data.data

                    LazyVerticalGrid(columns = GridCells.Fixed(numberOfColumns)) {
                        items(productsBrands) { dataCategories ->
                            CategoriesItem(
                                dataCategories = dataCategories,
                                navHostController = navHostController
                            )
                        }
                    }
                }
            }
        }

    }

}