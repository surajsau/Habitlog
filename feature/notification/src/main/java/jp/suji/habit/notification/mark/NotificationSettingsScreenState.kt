package jp.suji.habit.notification.mark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberNotificationSettingsScreenState(): NotificationSettingsScreenState {
    return remember {
        NotificationSettingsScreenState()
    }

}

@Stable
class NotificationSettingsScreenState