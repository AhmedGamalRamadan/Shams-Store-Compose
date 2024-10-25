package com.ag.projects.shamsstorecompose.presentation.components.products.slider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ag.projects.domain.model.products.cart.response.Item
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey

@Composable
fun ShoppingCartItem(
    modifier: Modifier = Modifier,
    productItem: Item,
) {

    var productQuantity by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row {
                AsyncImage(
                    modifier = modifier
                        .size(80.dp),
                    model = productItem.product_detail.image,
                    contentDescription = productItem.product_detail.desc
                )

                Spacer(modifier = modifier.width(3.dp))

                Column(
                    modifier = modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = productItem.product_detail.name,
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(text = productItem.product_detail.desc, maxLines = 1)

                    Spacer(modifier = modifier.height(5.dp))

                    //Price
                    Row {
                        productItem.product_detail.price_after?.let {
                            Text(
                                text =
                                productItem.product_detail.price_after.toString() + productItem.product_detail.currency,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = productItem.product_detail.price.toString(),
                                color = Grey
                            )
                        } ?: run {
                            Text(
                                text = productItem.product_detail.price.toString() + " " + productItem.product_detail.currency,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }


                Image(

                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = stringResource(id = R.string.delete)
                )

            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
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
                    onClick = {}
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = stringResource(id = R.string.add)
                    )
                }

            }
        }

    }
}