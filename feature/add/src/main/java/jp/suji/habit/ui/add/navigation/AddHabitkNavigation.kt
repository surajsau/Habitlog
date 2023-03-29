package jp.suji.habit.ui.add.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import jp.suji.habit.ui.add.AddTaskScreen

const val AddTaskRoute = "addtask"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addTask(
    onTapDismiss: () -> Unit,
    onTaskAdded: () -> Unit
) {
    composable(
        route = AddTaskRoute,
        enterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up) },
        popExitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down) }
    ) {
        AddTaskScreen(
            onTapDismiss = onTapDismiss,
            onTaskAdded = onTaskAdded
        )
    }
}