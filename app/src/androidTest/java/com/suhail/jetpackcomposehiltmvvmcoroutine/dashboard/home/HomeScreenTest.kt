package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.suhail.jetpackcomposehiltmvvmcoroutine.AppNav
import com.suhail.jetpackcomposehiltmvvmcoroutine.MainActivity
import com.suhail.jetpackcomposehiltmvvmcoroutine.R
import com.suhail.jetpackcomposehiltmvvmcoroutine.Screen
import com.suhail.jetpackcomposehiltmvvmcoroutine.di.AppModule
import com.suhail.jetpackcomposehiltmvvmcoroutine.di.NetworkModule
import com.suhail.jetpackcomposehiltmvvmcoroutine.ui.theme.AppTheme
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.FakeHomeScreenRepository
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class)
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

//    @get:Rule(order = 2)
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule(order = 3)
//    var mainCoroutineRule = MainCoroutineRule()

    lateinit var context: Context
    lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()
        composeRule.setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Dashboard.route
                ) {
                    composable(route = Screen.Dashboard.route) {
                        viewModel = hiltViewModel()
                        HomeScreen(navHostController = navController, viewModel = viewModel)
                    }
                }
                // AppNav(navController = navController, startDestination = Screen.Dashboard.route)
            }

        }

    }

    @Test
    fun test_screen_is_open() {
        //composeRule.onNodeWithText("Show Tickers").performClick()
        composeRule.onNodeWithTag("TickerList").assertIsDisplayed()
    }

    @After
    fun tearDown() {
//        composeRule.unregisterIdlingResource(idlingResource as FakeHomeScreenRepository)
    }
}