package jp.suji.habit.ui.habit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.fake.FakeHabit
import jp.suji.habit.model.HabitColor
import jp.suji.habit.model.HabitDay
import jp.suji.habit.ui.core.HabitColor

@Composable
fun HabitYear(
    modifier: Modifier = Modifier,
    color: Color,
    days: List<HabitDay>
) {
    LazyHorizontalGrid(
        modifier = modifier,
        rows = GridCells.Fixed(count = 7)
    ) {
        itemsIndexed(
            items = days,
            key = { _, day -> day.dayOfYear }
        ) { index, day ->
            HabitDayItem(
                modifier = Modifier
                    .padding(
                        top = 1.dp,
                        bottom = 1.dp,
                        end = if (index == days.size - 1) 16.dp else 1.dp,
                        start = if (index in 0..6) 16.dp else 1.dp
                    ),
                isCompleted = day.completed,
                color = color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewHabitYear() {
    HabitYear(
        modifier = Modifier.height(72.dp),
        color = HabitColor[FakeHabit.color.index],
        days = FakeHabit.days
    )
}