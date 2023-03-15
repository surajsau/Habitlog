package jp.suji.habit.di

import android.content.Context
import jp.suji.habit.data.HabitRepository

interface DataScope {
    val habitRepository: HabitRepository
}

val Context.dataScope: DataScope
    get() = applicationContext as DataScope