package com.ag.projects.shamsstorecompose.presentation.components.products


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ag.projects.domain.model.home.Content
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Red

@Composable
fun ProductItemCard(
    modifier: Modifier = Modifier,
    content: Content
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(300.dp)
            .padding(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            // salesPercent and favorite icon
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Card(
                    modifier = modifier.wrapContentSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = Red
                    ),
                    shape = RoundedCornerShape(3.dp)
                ) {
                    Text(
                        text = "20% Sale",
                        color = White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = White
                    ),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_favorite_not_selected),
                        contentDescription = stringResource(id = R.string.favorite)
                    )
                }
            }

            Spacer(modifier = modifier.height(16.dp))

            AsyncImage(
                model = content.image,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = modifier.height(7.dp))
            Text(
                text = content.name ?: "",
                maxLines = 1,
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                content.total_price?.let {
                    Text(
                        text = it.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = modifier.width(5.dp))
                content.price?.let {
                    Text(
                        text = it.toString(),
                        color = Color.DarkGray,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = modifier.padding(start = 7.dp)
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cart_available),
                        contentDescription = stringResource(id = R.string.shoppingcart)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = stringResource(id = R.string.minus)
                        )
                    }

                    Text(
                        text = "10",
                        modifier = modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = stringResource(id = R.string.add))
                    }

                }
            }
        }
    }
}
