package com.suhail.jetpackcomposehiltmvvmcoroutine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreen
import com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home.HomeScreenViewModel
import com.suhail.jetpackcomposehiltmvvmcoroutine.login.LoginScreen
import com.suhail.jetpackcomposehiltmvvmcoroutine.login.RegisterScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.suhail.jetpackcomposehiltmvvmcoroutine.settings.SettingsScreen

const val SCREEN_LOGIN = "LoginScreen"
const val SCREEN_REGISTER = "RegisterScreen"
const val SCREEN_HOME = "HomeScreen"
const val SCREEN_SETTINGS = "SettingsScreen"
const val ROUTE_LOGIN = "Login"
const val ROUTE_DASHBOARD = "Dashboard"

internal sealed class Screen(val route: String) {
    object Login: Screen(ROUTE_LOGIN)
    object Dashboard: Screen(ROUTE_DASHBOARD)
}

//https://api.coindcx.com/exchange/ticker

@Composable
fun AppNav(navController: NavHostController,
           startDestination: String = Screen.Dashboard.route
){
    NavHost(navController = navController,
        startDestination = startDestination){
        loginGraph(navController)
        dashBoard(navController)
    }
}

fun NavGraphBuilder.loginGraph(navController: NavHostController) {
    navigation(startDestination = SCREEN_LOGIN, route = Screen.Login.route) {
        composable(SCREEN_LOGIN) {
            LoginScreen(navHostController = navController)
        }
        composable(SCREEN_REGISTER) {
            RegisterScreen(navHostController = navController)
        }
    }
}

fun NavGraphBuilder.dashBoard(navController: NavHostController) {
    navigation(startDestination = SCREEN_HOME, route = Screen.Dashboard.route) {
        composable(SCREEN_HOME) {
//            val viewModel = hiltViewModel<HomeScreenViewModel>()

            val parentEntry = remember {
                navController.getBackStackEntry(Screen.Dashboard.route)
            }
            val parentViewModel = hiltViewModel<HomeScreenViewModel>(parentEntry)

            HomeScreen(navHostController = navController, parentViewModel)
        }
        composable(SCREEN_SETTINGS) {
            SettingsScreen()
        }
    }
}