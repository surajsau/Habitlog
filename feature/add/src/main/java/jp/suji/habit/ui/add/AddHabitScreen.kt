package jp.suji.habit.ui.add

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.add.components.IconChip
import jp.suji.habit.ui.add.components.TimePickerDialog
import jp.suji.habit.ui.core.HabitIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    state: AddHabitScreenState = rememberAddHabitScreenState(),
    onTapDismiss: () -> Unit,
    onTaskAdded: () -> Unit
) {
    DisposableEffect(state.dismiss) {
        if (state.dismiss) {
            onTaskAdded()
        }
        onDispose{}
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
                onChangeEnableNotification = onChangeEnableNotification,
                onTapNotificationTime = onTapNotificationTime,
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ColumnScope.TitleAndDescription(
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

@Composable
private fun ColumnScope.IconSelection(
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ColumnScope.NotificationSection(
    notificationEnabled: Boolean,
    notificationTime: String,
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