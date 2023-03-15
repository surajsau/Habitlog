package jp.suji.habit.ui.add.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.suji.habit.ui.add.AddTaskScreen

const val AddTaskRoute = "addtask"

fun NavGraphBuilder.addTask(
    onTapDismiss: () -> Unit,
    onTaskAdded: () -> Unit
) {
    composable(route = AddTaskRoute) {
        AddTaskScreen(
            onTapDismiss = onTapDismiss,
            onTaskAdded = onTaskAdded
        )
    }
}