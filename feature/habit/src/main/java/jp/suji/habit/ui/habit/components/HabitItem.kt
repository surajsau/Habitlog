package jp.suji.habit.ui.habit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.suji.habit.model.Habit
import jp.suji.habit.ui.core.HabitColor
import jp.suji.habit.ui.core.HabitIcons

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
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Image(
                painter = painterResource(id = HabitIcons[habit.icon.index].res),
                contentDescription = habit.title,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = habit.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.weight(1f))

            FilledIconButton(
                modifier = Modifier.size(36.dp),
                onClick = onTapComplete,
                colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = HabitColor[habit.color.index].copy(alpha = if (habit.completedToday) 1f else 0.5f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = if (habit.completedToday) Icons.Filled.Check else Icons.Filled.Add,
                    contentDescription = "${if (habit.completedToday) "Uncheck" else "Check"} ${habit.title}",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        HabitYear(
            modifier = Modifier.height(72.dp),
            color = HabitColor[habit.color.index],
            days = habit.days
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

private class HabitItemParamProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitItem(@PreviewParameter(HabitItemParamProvider::class) param: Boolean) {
//    HabitItem(
//        modifier = Modifier.padding(16.dp),
//        habit = FakeHabit.copy(completedToday = param),
//        onTapComplete = {}
//    )
}