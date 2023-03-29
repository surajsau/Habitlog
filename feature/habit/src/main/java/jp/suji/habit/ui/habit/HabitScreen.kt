package jp.suji.habit.ui.habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.suji.habit.fake.CompletedHabit
import jp.suji.habit.fake.LessCompletedHabit
import jp.suji.habit.fake.NotCompletedHabit
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitId
import jp.suji.habit.ui.core.R
import jp.suji.habit.ui.habit.components.HabitItem

@Composable
fun HabitScreen(
    modifier: Modifier = Modifier,
    state: HabitScreenState = rememberHabitScreenState(),
    onTapSettings: () -> Unit,
    onTapAddTask: () -> Unit
) {
    HabitScreenImpl(
        modifier = modifier,
        state = state.uiState,
        onTapComplete = state::onTapComplete,
        onTapSettings = onTapSettings,
        onTapAddTask = onTapAddTask
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HabitScreenImpl(
    modifier: Modifier = Modifier,
    state: HabitScreenState.UiState,
    onTapComplete: (HabitId) -> Unit,
    onTapSettings: () -> Unit,
    onTapAddTask: () -> Unit,
) {
    Box(modifier = modifier) {
        Column {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = ""
                        )
                        Text(text = stringResource(id = R.string.app_name))
                    }
                },
                actions = {
                    IconButton(onClick = onTapSettings) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = stringResource(id = R.string.content_desc_settings)
                        )
                    }
                }
            )
    
            when (state) {
                is HabitScreenState.UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
    
                is HabitScreenState.UiState.Data -> {
                    HabitsList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        habits = state.habits,
                        onTapComplete = onTapComplete
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = onTapAddTask
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = stringResource(id = R.string.content_desc_open_add_task)
            )
        }
    }
}

context(ColumnScope)
@Composable
internal fun HabitsList(
    modifier: Modifier = Modifier,
    habits: List<Habit>,
    onTapComplete: (HabitId) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(habits, key = { it.id.value }) { habit ->
            HabitItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                habit = habit,
                onTapComplete = { onTapComplete(habit.id) }
            )
        }
    }
}

internal class PreviewHabitScreenParameterProvider: PreviewParameterProvider<HabitScreenState.UiState> {

    override val values: Sequence<HabitScreenState.UiState>
        get() = sequenceOf(
            HabitScreenState.UiState.Loading,
            HabitScreenState.UiState.Data(
                listOf(
                    NotCompletedHabit,
                    CompletedHabit,
                    LessCompletedHabit
                )
            )
        )
}

@Preview(showBackground = true)
@Composable
internal fun PreviewHabitScreen(
    @PreviewParameter(PreviewHabitScreenParameterProvider::class) state: HabitScreenState.UiState
) {
    Surface {
        HabitScreenImpl(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onTapComplete = {},
            onTapSettings = {},
            onTapAddTask = {}
        )
    }
}