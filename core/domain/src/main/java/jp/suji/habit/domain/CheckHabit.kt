package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository
import java.time.LocalDate

class CheckHabit constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(id: jp.suji.habit.model.HabitId) {
        val today = LocalDate.now()
        repository.addDay(
            id = id.value,
            dayOfYear = today.dayOfYear,
            year = today.year
        )
    }
}