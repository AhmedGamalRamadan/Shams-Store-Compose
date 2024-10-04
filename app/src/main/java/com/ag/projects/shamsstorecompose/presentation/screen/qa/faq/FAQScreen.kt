package com.ag.projects.shamsstorecompose.presentation.screen.qa.faq

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.qa.CustomExpandableRow
import com.ag.projects.shamsstorecompose.presentation.screen.qa.AppInfoViewModel
import com.ag.projects.shamsstorecompose.utils.Result

@Composable
fun FAQScreen(
    navHostController: NavHostController
) {

    var textSearchState by remember {
        mutableStateOf("")
    }
    val viewModel: AppInfoViewModel = hiltViewModel()
    val faqState by viewModel.faqState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CommonHeader(
            editTextValue = textSearchState,
            onValueChange = {
                textSearchState = it
            },
            screenName = stringResource(id = R.string.faq),
            onBackClick = {
                navHostController.navigateUp()
            },
            iconBack = painterResource(id = R.drawable.ic_arrow_back),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            when (faqState) {
                is Result.Error -> {
                    Text(
                        text = stringResource(id = R.string.no_data_available),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Result.Loading ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.loading),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                is Result.Success -> {
                    val faqItemsList = (faqState as Result.Success).data.data
                    LazyColumn {
                        items(faqItemsList) { policyItems ->
                            CustomExpandableRow(
                                question = policyItems.question.toString(),
                                answer = policyItems.answer.toString()
                            )
                        }
                    }
                }
            }

        }
    }

}