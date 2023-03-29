package jp.suji.habit.fake

import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitColor
import jp.suji.habit.model.HabitDay
import jp.suji.habit.model.HabitIcon
import jp.suji.habit.model.HabitId

val NotCompletedHabit = Habit(
    id = HabitId(value = 1),
    title = "20 mins stroll",
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

val CompletedHabit = Habit(
    id = HabitId(value = 2),
    title = "Drink Water",
    icon = HabitIcon(index = 4),
    completedToday = true,
    color = HabitColor(index = 0),
    days = (1..365).map { dayOfYear ->
        HabitDay(
            year = 2023,
            dayOfYear = dayOfYear,
            completed = intArrayOf(
                287, 238, 334, 351, 318, 121, 17, 170, 41, 77, 346, 256, 241, 303, 336, 183, 347, 101,
                261, 263, 346, 312, 188, 104, 226, 297, 160, 296, 304, 347, 264, 73, 325, 125, 336, 255,
                143, 9, 285, 102, 87, 250, 283, 44, 257, 55, 113, 6, 34, 98, 263, 341, 57, 68, 323, 223,
                353, 174, 307, 4, 292, 307, 183, 317, 17, 163, 140, 248, 119, 165, 257, 244, 151, 97, 82,
                49, 324, 338, 133, 99, 119, 199, 153, 62, 303, 287, 103, 182, 110, 328, 286, 361, 338, 288,
                52, 192, 280, 65, 139, 355, 356
            ).contains(dayOfYear)
        )
    }
)

val LessCompletedHabit = Habit(
    id = HabitId(value = 3),
    title = "Sleep properly",
    icon = HabitIcon(index = 6),
    completedToday = false,
    color = HabitColor(index = 0),
    days = (1..365).map { dayOfYear ->
        HabitDay(
            year = 2023,
            dayOfYear = dayOfYear,
            completed = intArrayOf(
                45, 80, 51, 60, 45, 87, 72, 87, 79, 21, 7, 64, 10, 80, 79, 2, 58, 33, 32, 33, 77
            ).contains(dayOfYear)
        )
    }
)