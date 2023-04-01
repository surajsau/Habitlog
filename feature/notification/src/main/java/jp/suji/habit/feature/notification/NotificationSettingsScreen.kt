package jp.suji.habit.feature.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import jp.suji.habit.ui.core.R

@Composable
fun NotificationSettingsScreen(
    modifier: Modifier = Modifier,
    onTapBack: () -> Unit,
    state: NotificationSettingsScreenState = rememberNotificationSettingsScreenState()
) {

    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_notification_settings)) },
            navigationIcon = {
                IconButton(onClick = onTapBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
            }
        )
    }
}