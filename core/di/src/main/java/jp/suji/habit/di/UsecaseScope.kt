package jp.suji.habit.di

import android.content.Context
import jp.suji.habit.domain.AddHabit
import jp.suji.habit.domain.CheckHabit
import jp.suji.habit.domain.DeleteHabit
import jp.suji.habit.domain.UncheckHabit
import jp.suji.habit.domain.WatchHabitlog

context(AppScope)
class UsecaseScope {
    fun addHabit(): AddHabit = AddHabit(repository = habitRepository)
    fun checkHabit(): CheckHabit = CheckHabit(repository = habitRepository)
    fun deleteHabit(): DeleteHabit = DeleteHabit(repository = habitRepository)
    fun uncheckHabit(): UncheckHabit = UncheckHabit(repository = habitRepository)
    fun watchHabitlog(): WatchHabitlog = WatchHabitlog(repository = habitRepository)
}

val Context.usecaseScope: UsecaseScope
    get() = with(appScope) { UsecaseScope() }