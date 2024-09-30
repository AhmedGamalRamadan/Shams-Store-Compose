@file:OptIn(ExperimentalFoundationApi::class)

package com.ag.projects.shamsstorecompose.presentation.components.products.slider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ag.projects.shamsstorecompose.presentation.ui.theme.Grey
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay


@ExperimentalPagerApi
@Composable
fun ViewPagerSliderItem(
    modifier: Modifier = Modifier,
    imagesUrls: List<String>
) {

    val pagerState = rememberPagerState {
        imagesUrls.size
    }

    LaunchedEffect(key1 = pagerState) {
        while (true) {
            delay(1000L)
            val nextPage = (pagerState.currentPage + 1) % imagesUrls.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = imagesUrls.size,
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp)

        ) { page ->

            AsyncImage(
                model = imagesUrls[page],
                contentDescription = "Image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = imagesUrls.size,
            activeColor = Color.Black,
            inactiveColor = Grey,
            modifier = Modifier.padding(16.dp),
            indicatorWidth = 9.dp,
            indicatorHeight = 9.dp,
        )

    }
}