package com.ag.projects.shamsstorecompose.presentation.components.qa

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.ui.theme.LightBGQA

@Composable
fun CustomExpandableRow(
    question: String,
    answer: String,
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(LightBGQA)
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

        if (isExpanded){
            Text(
                text =  HtmlCompat.fromHtml(answer,HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }


}