package com.ag.projects.shamsstorecompose.presentation.components.products


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ag.projects.domain.model.products.home.Content
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Red
import com.ag.projects.shamsstorecompose.utils.Screen

@Composable
fun ProductItemCard(
    modifier: Modifier = Modifier,
    content: Content,
    navHostController: NavHostController,
    addToCart: ((Int, Int) -> Unit)? = null
) {

    var isFavorite by remember {
        mutableStateOf(false)
    }
    var productQuantity by remember {
        mutableIntStateOf(1)
    }


    Card(
        modifier = modifier
            .width(200.dp)
            .height(300.dp)
            .padding(7.dp)
            .clickable {
                navHostController.navigate(Screen.Details.rout + "/${content.id}")
            },
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

                content.percentage?.let {
                    Card(
                        modifier = modifier.wrapContentSize(),
                        colors = CardDefaults.cardColors(
                            containerColor = Red
                        ),
                        shape = RoundedCornerShape(3.dp)
                    ) {
                        Text(
                            text = "$it% Sale",
                            color = White,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                    },
                    modifier = modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = White
                    ),
                ) {
                    if (isFavorite) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_favorite_selected),
                            contentDescription = "Favorite"
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_favorite_not_selected),
                            contentDescription = "Favorite"
                        )
                    }
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

                content.price_after?.let {
                    Text(
                        text = "${content.price_after} ${content.currency}",
                        color = Black,
                    )
                    Text(
                        modifier = Modifier
                            .padding(6.dp),
                        textDecoration = TextDecoration.LineThrough,
                        text = content.price.toString(),
                        color = Grey
                    )
                } ?: Text(
                    text = "${content.price} ${content.currency}",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    addToCart?.invoke(content.id, productQuantity)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cart_available),
                        contentDescription = stringResource(id = R.string.shoppingcart)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (productQuantity > 1) {
                                productQuantity--
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = stringResource(id = R.string.minus)
                        )
                    }

                    Text(
                        text = "$productQuantity",
                        modifier = modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            productQuantity++
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = stringResource(id = R.string.add)
                        )
                    }

                }
            }
        }
    }
}
