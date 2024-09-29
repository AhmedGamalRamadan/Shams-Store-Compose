package com.ag.projects.shamsstorecompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Blue
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonHeader(
    modifier: Modifier = Modifier,
    editTextValue: String,
    onValueChange: (String) -> Unit,
    screenName: String,
    onBackClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Blue)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = stringResource(id = R.string.notifications),
                modifier = modifier
                    .width(60.dp)
                    .height(60.dp),
                tint = Color.White

            )
            Spacer(modifier = Modifier.height(5.dp))

            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = stringResource(id = R.string.logo)
            )
        }

        OutlinedTextField(
            value = editTextValue,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 14.dp, end = 14.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.search_products))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search_products),
                    tint = Grey
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Color.White

            )
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(LightGrey),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = screenName,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    var state by remember {
        mutableStateOf("")
    }
    CommonHeader(
        editTextValue = state,
        onValueChange = {
            state = it
        }, screenName = "Favorite", onBackClick = {}
    )
}