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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                .height(50.dp)
                .background(LightBGQA)
                .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = question,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            if (isExpanded) {
                Image(
                    modifier = Modifier
                        .padding(3.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = stringResource(id = R.string.go_to)
                )
            } else {
                Image(
                    modifier = Modifier
                        .padding(3.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_go_to),
                    contentDescription = stringResource(id = R.string.go_to)
                )
            }

        }
        if (isExpanded) {
            Text(
                text = HtmlCompat.fromHtml(answer, HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}