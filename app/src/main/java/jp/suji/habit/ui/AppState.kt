package jp.suji.habit.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import jp.suji.habit.ui.habit.navigation.HabitRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(): AppState {
    val navController = rememberAnimatedNavController()
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