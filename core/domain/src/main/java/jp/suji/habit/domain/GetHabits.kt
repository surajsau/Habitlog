package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository
import jp.suji.habit.data.HabitWithLog
import jp.suji.habit.model.Habit
import jp.suji.jp.domain.map.map

class GetHabits constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(): List<Habit> = repository
        .getHabits()
        .map(HabitWithLog::map)
}