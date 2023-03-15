package jp.suji.habit

import android.app.Application
import androidx.room.Room
import jp.suji.habit.di.DataScope
import jp.suji.habit.data.HabitDatabase
import jp.suji.habit.data.HabitRepository

class HabitlogApp: Application(), jp.suji.habit.di.DataScope {

    override val habitRepository by lazy {
        val db = Room
            .databaseBuilder(context = this, klass = HabitDatabase::class.java, name = "HabitLog")
            .build()

        val habitDao = db.habitDao()
        val logDao = db.logDao()

        return@lazy HabitRepository(habitDao = habitDao, logDao = logDao)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: HabitlogApp
            private set
    }
}