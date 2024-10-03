package com.ag.projects.shamsstorecompose.presentation.screen.bottom_nav.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.bottom_sheet.LanguageSelectionBottomSheet
import com.ag.projects.shamsstorecompose.utils.localization.setAppLocale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoScreen(
    navHostController: NavHostController
) {

    var textSearchState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var selectedLanguage by remember { mutableStateOf("en") }
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            LanguageSelectionBottomSheet(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = { languageCode ->
                    selectedLanguage = languageCode
                    setAppLocale(context, selectedLanguage)
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        modifier = Modifier
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CommonHeader(
                editTextValue = textSearchState,
                onValueChange = {
                    textSearchState = it
                },
                screenName = stringResource(id = R.string.info),
                onBackClick = {
                    navHostController.navigateUp()
                },
                iconBack = painterResource(id = R.drawable.ic_arrow_back)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        R.string.current_language,
                        if (selectedLanguage == "en")
                            stringResource(id = R.string.english)
                        else stringResource(
                            id = R.string.arabic
                        )
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    coroutineScope.launch { sheetState.show() }
                }) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = stringResource(R.string.change_language)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.change_language))
                }
            }
        }
    }

}