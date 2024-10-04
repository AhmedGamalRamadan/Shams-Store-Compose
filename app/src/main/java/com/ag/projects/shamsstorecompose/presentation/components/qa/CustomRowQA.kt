package com.ag.projects.shamsstorecompose.presentation.components.qa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightWhiteBGQA

@Composable
fun CustomRowQA(
    title: String,
    onRowClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightWhiteBGQA)
            .height(60.dp)
            .padding(7.dp)
            .clickable {
                onRowClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_go_to),
            contentDescription = stringResource(id = R.string.go_to)
        )
    }
}