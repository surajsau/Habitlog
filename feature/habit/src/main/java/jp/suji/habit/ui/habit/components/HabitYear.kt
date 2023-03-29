package jp.suji.habit.ui.habit.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.suji.habit.fake.CompletedHabit
import jp.suji.habit.model.HabitDay
import jp.suji.habit.ui.core.HabitColor

@Composable
fun HabitYear(
    modifier: Modifier = Modifier,
    color: Color,
    days: List<HabitDay>
) {
    val density = LocalDensity.current
    /*
        width = 52 weeks * 8dp + 51 gaps * 4dp
        height = 7 days * 8dp + 6 gaps * 4dp
     */
    Canvas(
        modifier = modifier
            .size(width = 620.dp, height = 80.dp)
    ) {
        val gridSize = with(density) { 8.dp.toPx() }
        val gapSize = with(density) { 4.dp.toPx() }
        val cornerRadius = with(density) { 2.dp.toPx() }

        // 364 days
        for (week in 0..51) {
            for (dayOfWeek in 0..6) {
                val x = week * (gridSize + gapSize)
                val y = dayOfWeek * (gridSize + gapSize)

                val dayIndex = week * 7 + dayOfWeek

                drawRoundRect(
                    color = color,
                    topLeft = Offset(x, y),
                    size = Size(gridSize, gridSize),
                    alpha = if(days[dayIndex].completed) 1f else 0.5f,
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            }
        }

        // 365(+1) days
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewHabitYear() {
    HabitYear(
        modifier = Modifier.height(72.dp),
        color = HabitColor[CompletedHabit.color.index],
        days = CompletedHabit.days
    )
}