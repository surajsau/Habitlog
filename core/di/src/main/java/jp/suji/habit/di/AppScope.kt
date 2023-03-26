package jp.suji.habit.di

import android.content.Context
import androidx.work.WorkManager
import jp.suji.habit.data.HabitRepository

interface AppScope {
    val habitRepository: HabitRepository
    val workManager: WorkManager
}

val Context.appScope: AppScope
    get() = applicationContext as AppScope