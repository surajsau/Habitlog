package jp.suji.habit.fake

import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitColor
import jp.suji.habit.model.HabitDay
import jp.suji.habit.model.HabitIcon
import jp.suji.habit.model.HabitId

val FakeHabit = Habit(
    id = HabitId(value = 1),
    title = "Fake Habit",
    icon = HabitIcon(index = 0),
    color = HabitColor(index = 0),
    completedToday = false,
    days = (1..365).map { dayOfYear ->
        HabitDay(
            year = 2023,
            dayOfYear = dayOfYear,
            completed = intArrayOf(
                217, 73, 196, 17, 15, 28, 312, 282, 164, 338, 320, 332, 338, 54, 71, 188, 279, 72,
                46, 91, 66, 27, 299, 284, 293, 317, 77, 248, 266, 157, 159, 204, 364, 345, 178, 39,
                309, 94, 32, 110, 222, 154, 159, 196, 263, 175, 115, 243, 60, 3, 258, 132, 323, 306,
                339, 124, 103, 8, 313, 70, 116, 91, 125, 208, 237, 335, 97, 207, 196, 186, 190, 24,
                232, 249, 237, 43, 291, 134, 8, 127, 348, 103, 99, 46, 209, 48, 153, 272, 167, 275,
                334, 188, 53, 339, 334, 105
            ).contains(dayOfYear)
        )
    }
)