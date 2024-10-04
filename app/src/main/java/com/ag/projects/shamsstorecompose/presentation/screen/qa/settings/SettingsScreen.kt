package com.ag.projects.shamsstorecompose.presentation.screen.qa.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ag.projects.shamsstorecompose.R
import com.ag.projects.shamsstorecompose.presentation.components.CommonHeader
import com.ag.projects.shamsstorecompose.presentation.components.bottom_sheet.LanguageSelectionBottomSheet
import com.ag.projects.shamsstorecompose.utils.localization.setAppLocale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
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
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            coroutineScope.launch { sheetState.show() }
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_language),
                        contentDescription = stringResource(
                            id = R.string.language
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        modifier =Modifier.fillMaxWidth(0.7f),
                        text = stringResource(id = R.string.language),
                    )
                    Text(
                        text = stringResource(id = R.string.language),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }

}