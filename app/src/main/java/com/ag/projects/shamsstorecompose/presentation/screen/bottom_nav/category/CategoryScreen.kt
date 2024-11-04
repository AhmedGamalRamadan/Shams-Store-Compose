package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.CategoriesItem
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel
import com.ag.projects.shamsstorecompose.utils.network.NetworkConnection
import com.ag.projects.shamsstorecompose.utils.Result
import com.ag.projects.shamsstorecompose.utils.Screen

@Composable
fun CategoryScreen(
    navHostController: NavHostController
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val categoriesState by viewModel.categoriesState.collectAsState()

    viewModel.getAllCategories()
    val context = LocalContext.current

    val isWifiConnected = NetworkConnection.checkWifiConnection(context)


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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            CommonHeader(
                editTextValue = textSearchState,
                onValueChange = {
                    textSearchState = it
                },
                screenName = stringResource(id = R.string.all_category),
                onBackClick = {
                    navHostController.navigateUp()
                },
                iconBack = painterResource(id = R.drawable.ic_arrow_back),
            )
        }

        item {
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
                when (categoriesState) {
                    is Result.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = (categoriesState as Result.Error).message)
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

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(numberOfColumns),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .padding(12.dp)
                        ) {
                            items(productsCategory) { dataCategories ->
                                CategoriesItem(
                                    dataCategories = dataCategories,
                                    onItemClick = { categoryId ->
                                        navHostController.navigate(Screen.AllCategoriesBrands.rout + "/$categoryId/0")
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