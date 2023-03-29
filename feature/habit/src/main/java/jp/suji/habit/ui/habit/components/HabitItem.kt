package jp.suji.habit.ui.habit.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.suji.habit.fake.LessCompletedHabit
import jp.suji.habit.model.Habit
import jp.suji.habit.ui.core.HabitIcons

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HabitItem(
    modifier: Modifier = Modifier,
    habit: Habit,
    onTapComplete: () -> Unit
) {
    Card(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = HabitIcons[habit.icon.index].res),
                contentDescription = habit.title,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Text(
                modifier = Modifier.weight(1f),
                text = habit.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            FilledIconButton(
                modifier = Modifier.size(36.dp),
                onClick = onTapComplete,
                shape = MaterialTheme.shapes.medium,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = if (habit.completedToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    contentColor = if (habit.completedToday) MaterialTheme.colorScheme.onPrimary else Color.White
                )
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = if (habit.completedToday) Icons.Filled.Check else Icons.Filled.Add,
                    contentDescription = "${if (habit.completedToday) "Uncheck" else "Check"} ${habit.title}"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            HabitYear(
                color = MaterialTheme.colorScheme.primary /* HabitColor[habit.color.index] */,
                days = habit.days
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

internal class HabitItemParamProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
internal fun PreviewHabitItem(@PreviewParameter(HabitItemParamProvider::class) param: Boolean) {
    HabitItem(
        habit = LessCompletedHabit.copy(completedToday = param),
        onTapComplete = {}
    )
}