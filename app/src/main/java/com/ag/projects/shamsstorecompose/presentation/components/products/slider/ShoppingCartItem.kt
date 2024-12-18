package com.ag.projects.shamsstorecompose.presentation.components.products.slider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
    deleteItem: ((Int) -> Unit)? = null,
    addItem: ((Int, Int) -> Unit)? = null,

    ) {

    var productQuantity by remember {
        mutableIntStateOf(productItem.quantity)
    }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .height(160.dp),
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {

                AsyncImage(
                    modifier = modifier
                        .size(80.dp),
                    model = productItem.product_detail.image,
                    contentDescription = productItem.product_detail.desc
                )

                Spacer(modifier = modifier.width(3.dp))

                Column {
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
                                text = productItem.total_price.toString() + " " + productItem.product_detail.currency,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        // call add
                        if (productItem.quantity > 1) {
                            productQuantity -= 1
                            addItem?.invoke(productItem.product_detail.id, --productItem.quantity)
                        } else {
                            deleteItem?.invoke(productItem.id)
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
                        productQuantity += 1
                        addItem?.invoke(productItem.product_detail.id, ++productItem.quantity)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = stringResource(id = R.string.add)
                    )
                }

                IconButton(
                    onClick = {
                        deleteItem?.invoke(productItem.id)
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = stringResource(id = R.string.delete)
                    )
                }
            }
        }
    }
}