package jp.suji.habit.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.add.components.ColorChip
import jp.suji.habit.ui.add.components.IconChip
import jp.suji.habit.ui.core.HabitColor
import jp.suji.habit.ui.core.HabitIcons

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
        onTapColor = state::onTapColor,
        onTapIcon = state::onTapIcon,
        onChangeTitle = state::onChangeTitle,
        onChangeDescription = state::onChangeDescription,
        onTapDismiss = onTapDismiss,
        onTapAdd = state::onTapAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskScreenImpl(
    modifier: Modifier = Modifier,
    state: AddHabitScreenState.UiState,
    onTapColor: (Int) -> Unit,
    onTapIcon: (Int) -> Unit,
    onChangeTitle: (String) -> Unit,
    onChangeDescription: (String) -> Unit,
    onTapDismiss: () -> Unit,
    onTapAdd: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.title,
            onValueChange = onChangeTitle,
            placeholder = { Text(text = stringResource(id = R.string.hint_habit_title)) }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = state.description,
            onValueChange = onChangeDescription,
            placeholder = { Text(text = stringResource(id = R.string.hint_habit_description)) }
        )

        IconSelection(
            selectedIconIndex = state.selectedIconIndex,
            onTapIcon = onTapIcon
        )

        ColorSelection(
            selectedColorIndex = state.selectedColorIndex,
            onTapColor = onTapColor
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ColumnScope.IconSelection(
    selectedIconIndex: Int,
    onTapIcon: (Int) -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = stringResource(id = R.string.title_icon),
        style = MaterialTheme.typography.titleMedium
    )

    HabitIcons.chunked(6)
        .forEachIndexed { rowIndex, icons ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                icons.forEachIndexed { index, icon ->
                    val itemIndex = rowIndex * 6 + index
                    IconChip(
                        isSelected = itemIndex == selectedIconIndex,
                        onTap = { onTapIcon(itemIndex) },
                        icon = icon
                    )
                }
            }
        }
}

@Composable
private fun ColumnScope.ColorSelection(
    selectedColorIndex: Int,
    onTapColor: (Int) -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = stringResource(id = R.string.title_color),
        style = MaterialTheme.typography.titleMedium
    )

    HabitColor.chunked(6)
        .forEachIndexed { rowIndex, colors ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                colors.forEachIndexed { index, color ->
                    val itemIndex = rowIndex * 6 + index
                    ColorChip(
                        selected = itemIndex == selectedColorIndex,
                        color = color,
                        onTap = { onTapColor(itemIndex) }
                    )
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddTaskScreen() {
    AddTaskScreenImpl(
        state = AddHabitScreenState.UiState(),
        onTapDismiss = {},
        onChangeDescription = {},
        onChangeTitle = {},
        onTapColor = {},
        onTapIcon = {},
        onTapAdd = {}
    )
}