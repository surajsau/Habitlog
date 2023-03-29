package jp.suji.habit.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.settings.component.ChangeThemeDialog

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsScreenState = rememberSettingsScreenState(),
    onTapDismiss: () -> Unit,
    onTapNotificationSettings: () -> Unit,
    onTapLicense: () -> Unit
) {
    if (state.changeThemeDialogShown) {
        ChangeThemeDialog(
            onDismiss = state::onDismissChangeThemeDialog
        )
    }

    SettingsScreenImpl(
        modifier = modifier,
        onTapDismiss = onTapDismiss,
        onTapChangeTheme = state::onTapChangeTheme,
        onTapNotificationSettings = onTapNotificationSettings,
        onTapLicense = onTapLicense
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreenImpl(
    modifier: Modifier = Modifier,
    onTapDismiss: () -> Unit,
    onTapChangeTheme: () -> Unit,
    onTapNotificationSettings: () -> Unit,
    onTapLicense: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_settings)) },
            navigationIcon = {
                IconButton(onClick = onTapDismiss) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.content_desc_dismiss_settings)
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
        ){
            ListItem(
                modifier = Modifier.clickable(onClick = onTapChangeTheme),
                headlineContent = { Text(text = "Change theme") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_theme),
                        contentDescription = "Change theme"
                    )
                }
            )

            Divider()

            ListItem(
                modifier = Modifier.clickable(onClick = onTapNotificationSettings),
                headlineContent = { Text(text = "Notification settings") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification_settings),
                        contentDescription = "Change theme"
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron),
                        contentDescription = stringResource(id = R.string.content_desc_notification_settings_trailing_icon)
                    )
                }
            )

            Spacer(modifier = Modifier.height(36.dp))

            ListItem(
                modifier = Modifier.clickable(onClick = onTapLicense),
                headlineContent = { Text(text = stringResource(id = R.string.settings_title_license)) },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron),
                        contentDescription = stringResource(id = R.string.content_desc_license_trailing_icon)
                    )
                }
            )


            // version number text center aligned
            Text(
                text = "v${BuildConfig.VERSION}",
                modifier = Modifier
                    .alpha(0.5f)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        modifier = Modifier.fillMaxSize(),
        onTapDismiss = {},
        onTapNotificationSettings = {},
        onTapLicense = {}
    )
}