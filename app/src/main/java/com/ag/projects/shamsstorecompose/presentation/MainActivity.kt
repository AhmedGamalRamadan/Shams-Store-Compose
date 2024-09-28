package com.ag.projects.shamsstorecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ag.projects.shamsstorecompose.presentation.screen.HomeViewModel
import com.ag.projects.shamsstorecompose.presentation.ui.theme.ShamsStoreComposeTheme
import com.ag.projects.shamsstorecompose.utils.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShamsStoreComposeTheme {

                val viewModel: HomeViewModel = hiltViewModel()
                val state by viewModel.allProducts.collectAsState()

                when (state) {
                    is Result.Error -> {}
                    Result.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text ="Loading .. . . . .")
                        }
                    }

                    is Result.Success -> {
                        val content = (state as Result.Success).data.data.flatMap { it.content }
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(content) {

                                Text(text = content.toString())
                            }
                        }
                    }
                }

            }
        }
    }
}
