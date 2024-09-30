package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.category

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
fun CategoryScreen(
    navHostController: NavHostController
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val categoriesState by viewModel.categoriesState.collectAsState()

    viewModel.getAllCategories()

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    val numberOfColumns = when {
        screenWidthDp >= 600.dp -> 4 // Large screens
        screenWidthDp >= 400.dp -> 3 // Medium screens
        else -> 2 // Small screens
    }

    var textSearchState by remember {
        mutableStateOf("")
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
            screenName = "All Category",
            onBackClick = {
                navHostController.navigateUp()
            },
            iconBack = Icons.Default.ArrowBack,
            changeLocation = ""
        )

        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {

            when (categoriesState) {
                is Result.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text =(categoriesState as Result.Error).message.toString())
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
                    val productsCategory = (categoriesState as Result.Success).data.data

                    LazyVerticalGrid(columns = GridCells.Fixed(numberOfColumns)) {
                        items(productsCategory) {
                            CategoriesItem(
                                categoryImage = it.image,
                                categoryName = it.name.toString()
                            )

                        }
                    }
                }
            }
        }

    }
}