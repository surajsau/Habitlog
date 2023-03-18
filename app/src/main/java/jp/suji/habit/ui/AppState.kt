package jp.suji.habit.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jp.suji.habit.ui.habit.navigation.HabitRoute

@Composable
fun rememberAppState(): AppState {
    val navController = rememberNavController()
    return remember {
        AppState(navController = navController)
    }
}

@Stable
class AppState(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val showFloatingActionButton: Boolean
        @Composable get()  =currentDestination?.route == HabitRoute

    fun pop() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route = route)
    }
}