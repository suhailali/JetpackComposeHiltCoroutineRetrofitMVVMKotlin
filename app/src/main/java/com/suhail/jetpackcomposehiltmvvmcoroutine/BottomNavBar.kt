package com.suhail.jetpackcomposehiltmvvmcoroutine

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Login,
        NavigationItem.Home,
        NavigationItem.Settings
    )

    BottomNavigation(backgroundColor = colorResource(id = R.color.black)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title)},
                selectedContentColor = colorResource(id = R.color.teal_200),
                unselectedContentColor = colorResource(id = R.color.purple_500),
                selected = currentDestination?.hierarchy?.any{ it.route == item.route} == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                    }
                })
        }
    }
}

sealed class NavigationItem(var route: String, var icon:Int, var title: String) {
    object Login: NavigationItem(SCREEN_LOGIN, icon = R.drawable.ic_baseline_login_24, "Login")
    object Home: NavigationItem(SCREEN_HOME, icon = R.drawable.ic_baseline_home_24, "Home")
    object Settings: NavigationItem(SCREEN_SETTINGS, icon = R.drawable.ic_baseline_settings_24, "Settings")
}
