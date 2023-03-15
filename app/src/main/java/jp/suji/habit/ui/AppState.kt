package jp.suji.habit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

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
    fun pop() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route = route)
    }
}