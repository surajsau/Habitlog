package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository
import jp.suji.habit.model.HabitId
import java.time.LocalDate

class UncheckHabit constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(id: HabitId) {
        val today = LocalDate.now()
        repository.removeLog(
            id = id.value,
            dayOfYear = today.dayOfYear,
            year = today.year
        )
    }
}