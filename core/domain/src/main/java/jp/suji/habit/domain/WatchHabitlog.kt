package jp.suji.habit.domain

import android.util.Log
import jp.suji.habit.data.HabitRepository
import jp.suji.habit.data.HabitWithLog
import jp.suji.jp.domain.map.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WatchHabitlog constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(): Flow<List<jp.suji.habit.model.Habit>> = repository.watch()
        .map { it.map(HabitWithLog::map) }
}