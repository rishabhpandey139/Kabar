package com.example.limitlesstech.limitlessnews.presentation.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.limitlesstech.limitlessnews.presentation.navigation.Routes
import com.example.limitlesstech.limitlessnews.presentation.onboarding.components.OnboardingBottomSection
import com.example.limitlesstech.limitlessnews.presentation.onboarding.components.OnboardingPager
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    // 🔥 Pager state
    val pagerState = rememberPagerState(
        pageCount = { state.pages.size }
    )

    // 🔥 Coroutine scope
    val scope = rememberCoroutineScope()

    // 🔥 Sync current page
    LaunchedEffect(pagerState.currentPage) {

        viewModel.onPageChanged(
            pagerState.currentPage
        )
    }

    Scaffold(

        bottomBar = {

            OnboardingBottomSection(

                currentPage =
                    pagerState.currentPage,

                pageCount =
                    state.pages.size,

                isLastPage =
                    pagerState.currentPage ==
                            state.pages.lastIndex,

                onNextClick = {

                    // 🔥 Last page
                    if (
                        pagerState.currentPage ==
                        state.pages.lastIndex
                    ) {

                        scope.launch {

                            // 🔥 Save onboarding completed
                            viewModel.saveOnboardingCompleted()

                            navController.navigate(
                                Routes.Login
                            ) {

                                popUpTo(
                                    Routes.Onboarding
                                ) {
                                    inclusive = true
                                }
                            }
                        }

                    } else {

                        // 🔥 Next page
                        scope.launch {

                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )
        }

    ) { padding ->

        OnboardingPager(

            pagerState = pagerState,

            pages = state.pages,

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}