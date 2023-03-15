package jp.suji.habit.model

data class HabitDay(
    val year: Int,
    val dayOfYear: Int,
    val completed: Boolean
)