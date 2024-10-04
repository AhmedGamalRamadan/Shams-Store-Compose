package com.ag.projects.shamsstorecompose.presentation.components.qa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightBGQA

@Composable
fun CustomExpandableRow(
    question: String,
    answer: String,
) {

    var isExpanded = false


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightBGQA)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = question,
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_go_to),
                contentDescription = stringResource(id = R.string.go_to)
            )
        }

        Text(
            text = answer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp)
        )
    }


}