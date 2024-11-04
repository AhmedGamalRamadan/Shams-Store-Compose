package com.ag.projects.shamsstorecompose.presentation.components.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ag.projects.domain.model.address.DefaultAddress
import com.ag.projects.shamsstorecompose.R

@Composable
fun AddressCard(
    modifier: Modifier = Modifier,
    defaultAddress: DefaultAddress,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    isDefault: Boolean,
    onDefaultChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_location),
                contentDescription = stringResource(id = R.string.location_name),
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onEditClick() }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = defaultAddress.location_description.toString(),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = defaultAddress.place_description.toString(),
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { onDeleteClick() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete_location),
                        contentDescription = stringResource(id = R.string.delete),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Switch(
                    checked = isDefault,
                    onCheckedChange = onDefaultChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Green,
                        checkedTrackColor = Color.LightGray,
                        uncheckedThumbColor = Color.Black,
                        uncheckedTrackColor = Color.LightGray,
                    )
                )
            }
        }
    }
}