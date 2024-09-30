package com.ag.projects.shamsstorecompose.presentation.components.products

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ag.projects.shamsstorecompose.R

@Composable
fun BrandsItemCard(
    modifier: Modifier = Modifier,
    brandImage: String
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .size(
                width = 135.dp,
                height = 60.dp
            ),
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
            model = brandImage,
            contentDescription = stringResource(id = R.string.brand),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        )
    }
}