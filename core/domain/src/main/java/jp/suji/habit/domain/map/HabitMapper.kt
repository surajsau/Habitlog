package jp.suji.jp.domain.map

import jp.suji.habit.data.HabitWithLog
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitColor
import jp.suji.habit.model.HabitDay
import jp.suji.habit.model.HabitIcon
import jp.suji.habit.model.HabitId
import java.time.LocalDate

fun HabitWithLog.map(): Habit {
    val today = LocalDate.now()
    val completedToday = logs.any { it.year == today.year && it.dayOfYear == today.dayOfYear }

    // get days of current year only
    val currentYear = logs.filter { it.year == today.year }

    return Habit(
        id = HabitId(value = habit.id),
        title = habit.title,
        icon = HabitIcon(index = habit.icon),
        color = HabitColor(index = habit.color),
        completedToday = completedToday,
        days = (1..365).map { dayOfYear ->
            HabitDay(
                dayOfYear = dayOfYear,
                year = today.year,
                completed = currentYear.any { it.dayOfYear == dayOfYear }
            )
        }
    )
}