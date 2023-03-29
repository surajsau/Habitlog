package jp.suji.habit.ui.habit.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import jp.suji.habit.ui.habit.HabitScreen

const val HabitRoute = "habit"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.habit(
    onTapSettings: () -> Unit,
    onTapAddTask: () -> Unit
) {
    composable(route = HabitRoute) {
        HabitScreen(
            onTapSettings = onTapSettings,
            onTapAddTask = onTapAddTask
        )
    }
}