package com.ag.projects.shamsstorecompose.presentation.components.spinner_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ag.projects.domain.model.country.Country

@Composable
fun CountryItem(
    country: Country
) {

    Row(
        modifier = Modifier
            .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
            .wrapContentWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = country.image,
            contentDescription = country.name.toString(),
            modifier = Modifier
                .size(30.dp),
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = country.phone_code,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}