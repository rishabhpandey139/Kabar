package com.example.limitlesstech.limitlessnews.presentation.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.example.limitlesstech.limitlessnews.presentation.onboarding.model.OnboardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPager(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
    modifier: Modifier = Modifier
) {

    //  Disable overscroll effect
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {

        HorizontalPager(

            state = pagerState,

            modifier = modifier
                .fillMaxSize()

        ) { page ->

            OnboardingPageItem(
                page = pages[page]
            )
        }
    }
}