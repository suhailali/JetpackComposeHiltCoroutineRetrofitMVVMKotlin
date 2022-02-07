package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.FakeHomeScreenRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeScreenViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var homeScreenViewModel: HomeScreenViewModel

    @Before
    fun setUp() {
        homeScreenViewModel = HomeScreenViewModel(FakeHomeScreenRepository())
    }

    @Test
    fun `test whether returns a list`() = mainCoroutineRule.runBlockingTest {
        homeScreenViewModel.getTicker()
        val result = homeScreenViewModel.uiState
        assertThat(result.value).isInstanceOf(HomeScreenViewModel.UiState.Success::class.java)
    }
}