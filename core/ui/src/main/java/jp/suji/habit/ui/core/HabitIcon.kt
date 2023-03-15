package jp.suji.habit.ui.core

@JvmInline
value class HabitIcon(val res: Int)

val HabitIcons: List<HabitIcon> = listOf(
    HabitIcon(R.drawable.ic_sleep),
    HabitIcon(R.drawable.ic_read),
    HabitIcon(R.drawable.ic_walk),
    HabitIcon(R.drawable.ic_task),
    HabitIcon(R.drawable.ic_eat),
    HabitIcon(R.drawable.ic_landmark),
    HabitIcon(R.drawable.ic_dollar),
    HabitIcon(R.drawable.ic_rupee),
    HabitIcon(R.drawable.ic_bitcoin),
    HabitIcon(R.drawable.ic_piggy_bank)
)