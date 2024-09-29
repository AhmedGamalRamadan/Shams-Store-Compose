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
//                val viewModel: HomeViewModel = hiltViewModel()
//                val state by viewModel.allProducts.collectAsState()
//
//                when (state) {
//                    is Result.Error -> {}
//                    Result.Loading -> {
//                        Box(
//                            modifier = Modifier.fillMaxSize(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(text ="Loading .. . . . .")
//                        }
//                    }
//
//                    is Result.Success -> {
//                        val content = (state as Result.Success).data.data.flatMap { it.content }
//                        LazyColumn(modifier = Modifier.fillMaxSize()) {
//                            items(content) {
//
//                                Text(text = content.toString())
//                            }
//                        }
//                    }
//                }

            }
        }
    }
}
