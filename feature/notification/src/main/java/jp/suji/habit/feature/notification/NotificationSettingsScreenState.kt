package jp.suji.habit.feature.notification

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