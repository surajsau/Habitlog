package jp.suji.habit.ui.habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitId
import jp.suji.habit.ui.habit.components.HabitItem
import jp.suji.habit.ui.core.R

@Composable
fun HabitScreen(
    modifier: Modifier = Modifier,
    state: HabitScreenState = rememberHabitScreenState(),
    onTapSettings: () -> Unit
) {
    HabitScreenImpl(
        modifier = modifier,
        habits = state.habits,
        onTapComplete = state::onTapComplete,
        onTapSettings = onTapSettings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HabitScreenImpl(
    modifier: Modifier = Modifier,
    habits: List<Habit>,
    onTapComplete: (HabitId) -> Unit,
    onTapSettings: () -> Unit
) {
    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(habits) { habit ->
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
}

@Preview(showBackground = true)
@Composable
fun PreviewHabitScreen() {
    HabitScreenImpl(
        modifier = Modifier.fillMaxSize(),
        habits = listOf(
//            FakeHabit,FakeHabit
//            FakeHabit.copy(color = HabitColor[1]),
//            FakeHabit.copy(color = HabitColor[2])
        ),
        onTapComplete = {},
        onTapSettings = {}
    )
}