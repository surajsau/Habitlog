package jp.suji.habit.model

data class Habit(
    val id: HabitId,
    val title: String,
    val icon: HabitIcon,
    val color: HabitColor,
    val completedToday: Boolean,
    val days: List<HabitDay>
)