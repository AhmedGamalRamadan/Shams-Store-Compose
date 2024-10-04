package com.ag.projects.shamsstorecompose.presentation.components.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ag.projects.shamsstorecompose.R

@Composable
fun LanguageSelectionBottomSheet(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.select_language),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        val radioOptions = listOf("English" to "en", "Arabic" to "ar")

        radioOptions.forEach { (label, code) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedLanguage == code,
                    onClick = { onLanguageSelected(code) },
                )
                if (label == "English")
                    Image(
                        painter = painterResource(id = R.drawable.ic_american_flag),
                        contentDescription = stringResource(
                            id = R.string.english
                        )
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.ic_sadia_flag),
                        contentDescription = stringResource(
                            id = R.string.english
                        )
                    )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label)

            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}