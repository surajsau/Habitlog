package jp.suji.habit.ui.habit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.suji.habit.ui.habit.HabitScreen

const val HabitRoute = "habit"

fun NavGraphBuilder.habit(
    onTapSettings: () -> Unit,
) {
    composable(route = HabitRoute) {
        HabitScreen(
            onTapSettings = onTapSettings
        )
    }
}