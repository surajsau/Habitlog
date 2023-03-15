package jp.suji.habit.exception

class HabitNotFoundException(id: Int): Exception() {
    override val message: String = "Habit for $id not found"
}