package jp.suji.habit.ui.settings

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import jp.suji.habit.fake.FakeHabit
import jp.suji.habit.model.HabitId
import jp.suji.habit.ui.core.R

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: SettingsScreenState = rememberSettingsScreenState(),
    onTapDismiss: () -> Unit
) {
    SettingsScreenImpl(
        modifier = modifier,
        onTapDismiss = onTapDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
private fun SettingsScreenImpl(
    modifier: Modifier = Modifier,
    onTapDismiss: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_settings)) },
            navigationIcon = {
                IconButton(onClick = onTapDismiss) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.content_desc_dismiss_settings)
                    )
                }
            }
        )
    }
}