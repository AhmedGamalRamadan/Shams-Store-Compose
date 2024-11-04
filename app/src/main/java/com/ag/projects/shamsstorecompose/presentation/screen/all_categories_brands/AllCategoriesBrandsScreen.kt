package com.ag.projects.shamsstorecompose.presentation.screen.all_categories_brands

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.utils.NavArguments
import com.ag.projects.shamsstorecompose.utils.Result
@Composable
fun AllCategoriesBrandsScreen(
    backStackEntry: NavBackStackEntry
) {

    val categoryId =backStackEntry.arguments?.getInt(NavArguments.CATEGORY_ID) ?:0
    val brandId =backStackEntry.arguments?.getInt(NavArguments.BRAND_ID) ?:0

    val viewModel: AllCategoriesBrandsScreenViewModel = hiltViewModel()
    val allCategories by viewModel.allCategories.collectAsState()
    val allBrands by viewModel.allBrands.collectAsState()


    var textSearchState by remember {
        mutableStateOf("")
    }
    var categorySelectedIndex by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = Unit){
        viewModel.getAllCategories()
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
            screenName = stringResource(id = R.string.all_category),
            onBackClick = {},
        )

        Spacer(modifier = Modifier.height(7.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            when (allCategories) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    val categories = (allCategories as Result.Success).data.data
                    itemsIndexed(categories) { index, dataCategories ->
                        val isSelected = index == categorySelectedIndex
                        Button(
                            onClick = {
                                categorySelectedIndex = index
                            },
                           modifier = Modifier
                               .padding(8.dp)
                               .width(120.dp)
                               .height(80.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor =if (isSelected) Color.Blue else Color.White ,
                                contentColor =if (isSelected) Color.White else Color.Black ,
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
}