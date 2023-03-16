package jp.suji.habit.ui.habit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.suji.habit.fake.FakeHabit
import jp.suji.habit.ui.core.HabitColor

@Composable
fun HabitDayItem(
    modifier: Modifier = Modifier,
    isCompleted: Boolean,
    color: Color
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .background(color = color.copy(alpha = if (isCompleted) 1f else 0.5f), shape = RoundedCornerShape(2.dp))
    )
}

internal class HabitDayParameterProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
internal fun PreviewHabitDayItem(@PreviewParameter(HabitDayParameterProvider::class) parameter: Boolean) {
    HabitDayItem(
        isCompleted = parameter,
        color = HabitColor[FakeHabit.color.index]
    )
}