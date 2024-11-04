package com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ag.projects.data.local.SharedPreferencesManager
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.products.ProductItemCard
import com.ag.projects.shamsstorecompose.utils.NavArguments
import com.ag.projects.shamsstorecompose.utils.Result
import kotlinx.coroutines.launch

@Composable
fun AllCategoriesBrandsScreen(
    backStackEntry: NavBackStackEntry,
    navHostController: NavHostController
) {

    val categoryId = backStackEntry.arguments?.getInt(NavArguments.CATEGORY_ID) ?: 0
    val brandId = backStackEntry.arguments?.getInt(NavArguments.BRAND_ID) ?: 0


    val viewModel: AllCategoriesBrandsScreenViewModel = hiltViewModel()
    val allCategoriesState by viewModel.allCategoriesHeader.collectAsState()
    val products by viewModel.productsBrandCategories.collectAsState()

    val context = LocalContext.current
    val sharedPrefManager = SharedPreferencesManager(context)

    var textSearchState by remember {
        mutableStateOf("")
    }
    var categorySelectedIndex by remember {
        mutableIntStateOf(0)
    }

    var selectedCategoryId = 0
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.resetPagination()
        val categoryID = if (categoryId == 0) null else categoryId
        val brandID = if (brandId == 0) null else brandId

        viewModel.getCategoriesBrands(
            auth = "Bearer ${sharedPrefManager.getToken()}",
            categoryId = categoryID,
            brandId = brandID
        )
        if (categoryId != 0) {
            // get all brands
            viewModel.getAllCategoriesHeader()
        } else {
            // get all categories
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            CommonHeader(
                modifier = Modifier.fillMaxWidth(),
                editTextValue = textSearchState,
                onValueChange = {
                    textSearchState = it
                },
                screenName = if (categoryId == 0) stringResource(id = R.string.all_brands) else stringResource(
                    id = R.string.all_category
                ),
                onBackClick = {},
            )
            Spacer(modifier = Modifier.height(7.dp))
        }

        item {
            if (brandId == 0) {
                // get categories as a tabs
                when (allCategoriesState) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        val categories = (allCategoriesState as Result.Success).data.data

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            itemsIndexed(categories) { index, dataCategories ->
                                val isSelected = index == categorySelectedIndex
                                Button(
                                    onClick = {
                                        scope.launch {
                                            viewModel.resetPagination()
                                            categorySelectedIndex = index

                                            selectedCategoryId = dataCategories.id
                                            viewModel.getCategoriesBrands(
                                                auth = "Bearer ${sharedPrefManager.getToken()}",
                                                categoryId = dataCategories.id,
                                                brandId = null
                                            )
                                        }

                                    },
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .width(120.dp)
                                        .height(80.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = if (isSelected) Color.Blue else Color.White,
                                        contentColor = if (isSelected) Color.White else Color.Black,
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                ) {
                                    Text(text = dataCategories.name.toString())
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = stringResource(
                            id = R.string.filter
                        )
                    )
                }
                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = stringResource(
                            id = R.string.sort
                        )
                    )
                }

                IconButton(onClick = {}) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_grid_view),
                        contentDescription = stringResource(
                            id = R.string.grid
                        )
                    )
                }

                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_linear_view),
                        contentDescription = stringResource(
                            id = R.string.linear
                        )
                    )
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))

            when (products) {
                is Result.Error -> {
                    Text(text = (products as Result.Error).message)
                }
                Result.Loading -> {
                    Text(text = stringResource(id = R.string.loading))
                }
                is Result.Success -> {
                    val productsContent = (products as Result.Success).data
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        columns = GridCells.Fixed(2)
                    ) {
                        itemsIndexed(productsContent) { index, content ->
                            ProductItemCard(
                                content = content,
                                navHostController = navHostController
                            )
                            if (index >= productsContent.size - 1) {
                                LaunchedEffect(key1 = Unit) {
                                    viewModel.loadNextPage(
                                        auth = "Bearer ${sharedPrefManager.getToken()}",
                                        categoryId = selectedCategoryId,
                                        brandId = null
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