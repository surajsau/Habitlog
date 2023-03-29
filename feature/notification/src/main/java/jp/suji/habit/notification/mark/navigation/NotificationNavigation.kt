package jp.suji.habit.notification.mark.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import jp.suji.habit.notification.mark.NotificationSettingsScreen

val NotificationSettingsRoute = "notification_settings"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.notificationSettings(
    onTapBack: () -> Unit
) {
    composable(route = NotificationSettingsRoute) {
        NotificationSettingsScreen(
            onTapBack = onTapBack
        )
    }
}