package com.ag.projects.shamsstorecompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProductCatalogCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    productName: String
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .size(width = 140.dp,
                height = 20.dp),
        shape = RoundedCornerShape(14.dp),
        colors =  CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 9.dp,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription ="",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )

            Text(
                text = productName,
                fontSize = 17.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
