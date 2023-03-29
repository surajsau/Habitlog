package jp.suji.habit.ui.add

import android.content.Context
import android.content.IntentFilter
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import jp.suji.habit.ext.LifecycleEffect
import jp.suji.habit.ext.isAboveTiramisu
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.add.components.IconChip
import jp.suji.habit.ui.add.components.TimePickerDialog
import jp.suji.habit.ui.add.worker.mark.MarkHabitCompleteReceiver
import jp.suji.habit.ui.core.HabitIcons

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    state: AddHabitScreenState = rememberAddHabitScreenState(),
    onTapDismiss: () -> Unit,
    onTaskAdded: () -> Unit
) {
    val context = LocalContext.current

    DisposableEffect(state.dismiss) {
        if (state.dismiss) {
            onTaskAdded()
        }
        onDispose{}
    }

    LifecycleEffect {
        when(it) {
            Lifecycle.Event.ON_CREATE -> {
                if (isAboveTiramisu()) {
                    context.registerReceiver(
                        MarkHabitCompleteReceiver(),
                        IntentFilter(MarkHabitCompleteReceiver.ACTION_NAME),
                        Context.RECEIVER_NOT_EXPORTED
                    )
                } else {
                    context.registerReceiver(
                        MarkHabitCompleteReceiver(),
                        IntentFilter(MarkHabitCompleteReceiver.ACTION_NAME),
                    )
                }
            }

            Lifecycle.Event.ON_DESTROY -> {
                runCatching {
                    context.unregisterReceiver(MarkHabitCompleteReceiver())
                }
            }
            else -> { /* do nothing */ }
        }
    }

    AddTaskScreenImpl(
        modifier = modifier,
        state = state.uiState,
        onTapIcon = state::onTapIcon,
        onChangeTitle = state::onChangeTitle,
        onChangeDescription = state::onChangeDescription,
        onChangeEnableNotification = state::onChangeNotificationEnabled,
        onTapNotificationTime = state::onTapNotificationTime,
        onTapDismiss = onTapDismiss,
        onTapAdd = state::onTapAdd
    )

    if (state.showTimePIcker) {
        TimePickerDialog(
            state = state.timePickerState,
            onRequestDismiss = state::onRequestDismissTimePicker,
            onTapDismiss = state::onTapTimePickerDismiss,
            onTapConfirm = state::onTapTimePickerConfirm
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskScreenImpl(
    modifier: Modifier = Modifier,
    state: AddHabitScreenState.UiState,
    onTapIcon: (Int) -> Unit,
    onChangeTitle: (String) -> Unit,
    onChangeDescription: (String) -> Unit,
    onChangeEnableNotification: (Boolean) -> Unit,
    onTapNotificationTime: () -> Unit,
    onTapDismiss: () -> Unit,
    onTapAdd: () -> Unit
) {
    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.surface)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_add_habit)) },
            navigationIcon = {
                IconButton(onClick = onTapDismiss) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(id = R.string.content_desc_dismiss_add)
                    )
                }
            },
            actions = {
                IconButton(onClick = onTapAdd) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = stringResource(id = R.string.content_desc_add)
                    )
                }
            }
        )

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            TitleAndDescription(
                title = state.title,
                description = state.description,
                onChangeTitle = onChangeTitle,
                onChangeDescription = onChangeDescription
            )

            IconSelection(
                selectedIconIndex = state.selectedIconIndex,
                onTapIcon = onTapIcon
            )

            NotificationSection(
                notificationEnabled = state.notificationEnabled,
                notificationTime = state.notificationTime,
                permissionRationale = state.permissionRationale,
                onChangeEnableNotification = onChangeEnableNotification,
                onTapNotificationTime = onTapNotificationTime,
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

context(ColumnScope)
@Composable
private fun TitleAndDescription(
    title: String,
    description: String,
    onChangeTitle: (String) -> Unit,
    onChangeDescription: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onChangeTitle,
            placeholder = { Text(text = stringResource(id = R.string.hint_habit_title)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            )
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = onChangeDescription,
            placeholder = { Text(text = stringResource(id = R.string.hint_habit_description)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            )
        )
    }
}

context(ColumnScope)
@Composable
private fun IconSelection(
    selectedIconIndex: Int,
    onTapIcon: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title_icon),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 6),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(HabitIcons) { index, icon ->
                IconChip(
                    isSelected = index == selectedIconIndex,
                    onTap = { onTapIcon(index) },
                    icon = icon
                )
            }
        }
    }

}

context(ColumnScope)
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NotificationSection(
    notificationEnabled: Boolean,
    notificationTime: String,
    permissionRationale: String?,
    onChangeEnableNotification: (Boolean) -> Unit,
    onTapNotificationTime: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_notification),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.title_notification_description),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))

            Switch(
                checked = notificationEnabled,
                onCheckedChange = onChangeEnableNotification
            )
        }

        AnimatedContent(
            targetState = permissionRationale != null,
            label = ""
        ) { showRationale ->
            if (showRationale) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.errorContainer,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = permissionRationale!!,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                )
            }
        }

        AnimatedContent(
            targetState = notificationEnabled,
            label = "",
        ) { enabled ->
            if (enabled) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.title_notification_time),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(onClick = onTapNotificationTime) {
                        Text(text = notificationTime)
                    }
                }
            }
            }
    }
}

//region Preview: Notification Section
internal class NotificationSectionPreviewParam: PreviewParameterProvider<NotificationSectionPreviewParam.Model> {

    override val values: Sequence<Model>
        get() = sequenceOf(
            Model(
                notificationTime = "12:00",
                notificationEnabled = false,
                permissionRationale = null
            ),
            Model(
                notificationTime = "07:00",
                notificationEnabled = false,
                permissionRationale = "Allow notifications"
            ),
            Model(
                notificationTime = "07:00",
                notificationEnabled = true,
                permissionRationale = null
            )
        )

    data class Model(
        val notificationTime: String,
        val notificationEnabled: Boolean,
        val permissionRationale: String?
    )
}

@Preview(showBackground = true)
@Composable
internal fun PreviewNotificationSection(
    @PreviewParameter(NotificationSectionPreviewParam::class) model: NotificationSectionPreviewParam.Model
) {
    Column {
        NotificationSection(
            notificationEnabled = model.notificationEnabled,
            notificationTime = model.notificationTime,
            permissionRationale = model.permissionRationale,
            onChangeEnableNotification = {},
            onTapNotificationTime = {}
        )
    }
}
//endregion

@Preview(showBackground = true)
@Composable
internal fun PreviewAddTaskScreen() {
    AddTaskScreenImpl(
        state = AddHabitScreenState.UiState(),
        onTapDismiss = {},
        onChangeDescription = {},
        onChangeTitle = {},
        onChangeEnableNotification = {},
        onTapNotificationTime = {},
        onTapIcon = {},
        onTapAdd = {}
    )
}