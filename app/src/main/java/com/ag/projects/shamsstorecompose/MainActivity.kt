package com.ag.projects.shamsstorecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ag.projects.shamsstorecompose.ui.theme.ShamsStoreComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShamsStoreComposeTheme {

            }
        }
    }
}
