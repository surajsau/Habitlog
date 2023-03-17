package jp.suji.habit.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import jp.suji.habit.MainActivity
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitColor
import jp.suji.habit.model.HabitIcon
import jp.suji.habit.model.HabitId
import jp.suji.habit.notification.HabitNotification
import jp.suji.habit.notification.HabitNotificationChannelId
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.add.navigation.AddTaskRoute
import jp.suji.habit.ui.add.navigation.addTask
import jp.suji.habit.ui.core.AppTheme
import jp.suji.habit.ui.core.HabitColor
import jp.suji.habit.ui.habit.navigation.HabitRoute
import jp.suji.habit.ui.habit.navigation.habit
import jp.suji.habit.ui.settings.navigation.SettingsRoute
import jp.suji.habit.ui.settings.navigation.settings

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun App(
    modifier: Modifier = Modifier,
    state: AppState = rememberAppState()
) {
    val context = LocalContext.current
    AppTheme {
        Surface(modifier = modifier) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(onClick = { state.navigate(route = AddTaskRoute) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = stringResource(id = R.string.content_desc_open_add_task)
                        )
                    }
                }
            ) {
                NavHost(
                    modifier = Modifier.padding(it),
                    navController = state.navController,
                    startDestination = HabitRoute
                ) {
                    addTask(
                        onTapDismiss = state::pop,
                        onTaskAdded = state::pop
                    )

                    habit(
                        onTapSettings = { state.navigate(route = SettingsRoute) }
                    )

                    settings(onTapDismiss = state::pop)
                }
            }
        }
    }
}