package com.ag.projects.shamsstorecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ag.projects.shamsstorecompose.presentation.navigation.Navigation
import com.ag.projects.shamsstorecompose.presentation.ui.theme.ShamsStoreComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShamsStoreComposeTheme {
                Navigation()
            }
        }
    }
}
