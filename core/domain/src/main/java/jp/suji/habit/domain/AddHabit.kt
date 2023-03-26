package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository
import java.time.LocalDate

class AddHabit constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(
        title: String,
        icon: Int,
        color: Int
    ): Int {
        return repository.insert(title = title, icon = icon, color = color)
    }
}