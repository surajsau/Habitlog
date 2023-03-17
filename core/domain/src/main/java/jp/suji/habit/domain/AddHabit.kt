package jp.suji.habit.domain

import jp.suji.habit.data.HabitRepository
import java.time.LocalDate

class AddHabit constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(
        title: String,
        icon: Int,
        color: Int,
        enableNotification: Boolean,
        notificationHour: Int,
        notificationMinute: Int
    ) {
        //TODO fire up periodic work request
        repository.insert(title = title, icon = icon, color = color)
    }
}