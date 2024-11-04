package com.ag.projects.shamsstorecompose.presentation.components.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ag.projects.domain.model.products.brand.DataCategories
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.DarkBlue

@Composable
fun CategoriesItem(
    dataCategories: DataCategories,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = modifier
                .size(120.dp)
                .padding(8.dp)
                .clickable {
                    onItemClick(dataCategories.id)
                },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.White,
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 9.dp,
            ),
        ) {
            AsyncImage(
                model = dataCategories.image,
                contentDescription = stringResource(id = R.string.brand),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            )
        }

        Text(
            text = dataCategories.name.toString(),
            color = DarkBlue,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

    }
}