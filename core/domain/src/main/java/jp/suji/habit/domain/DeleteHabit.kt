package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository

class DeleteHabit constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(id: jp.suji.habit.model.HabitId, dayOfYear: Int, year: Int) {
        repository.removeLog(
            id = id.value,
            dayOfYear = dayOfYear,
            year = year
        )
    }
}