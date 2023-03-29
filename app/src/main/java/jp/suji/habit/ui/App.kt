package jp.suji.habit.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import jp.suji.habit.notification.mark.navigation.notificationSettings
import jp.suji.habit.ui.add.navigation.AddTaskRoute
import jp.suji.habit.ui.add.navigation.addTask
import jp.suji.habit.ui.core.AppTheme
import jp.suji.habit.ui.habit.navigation.HabitRoute
import jp.suji.habit.ui.habit.navigation.habit
import jp.suji.habit.ui.settings.navigation.LicenseRoute
import jp.suji.habit.ui.settings.navigation.SettingsRoute
import jp.suji.habit.ui.settings.navigation.settings

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    state: AppState = rememberAppState()
) {
    AppTheme {
        Surface(modifier = modifier) {
            AnimatedNavHost(
                navController = state.navController,
                startDestination = HabitRoute
            ) {
                addTask(
                    onTapDismiss = state::pop,
                    onTaskAdded = state::pop
                )

                habit(
                    onTapSettings = { state.navigate(route = SettingsRoute) },
                    onTapAddTask = { state.navigate(route = AddTaskRoute) }
                )

                settings(
                    onTapDismiss = state::pop,
                    onTapNotificationSettings = {},
                    onTapLicense = { state.navigate(route = LicenseRoute) }
                )

                notificationSettings(onTapBack = state::pop)
            }
        }
    }
}