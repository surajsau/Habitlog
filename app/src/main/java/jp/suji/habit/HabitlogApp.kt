package jp.suji.habit

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.work.Configuration
import androidx.work.WorkManager
import jp.suji.habit.di.AppScope
import jp.suji.habit.data.HabitDatabase
import jp.suji.habit.data.HabitRepository
import jp.suji.habit.di.NotificationScope
import jp.suji.habit.notification.HabitNotificationManager

class HabitlogApp: Application(), AppScope, NotificationScope {

    override val habitRepository by lazy {
        val db = Room
            .databaseBuilder(context = this, klass = HabitDatabase::class.java, name = "HabitLog")
            .build()

        val habitDao = db.habitDao()
        val logDao = db.logDao()

        return@lazy HabitRepository(habitDao = habitDao, logDao = logDao)
    }

    override val workManager: WorkManager by lazy {
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.VERBOSE)
                .build()
        )

        return@lazy WorkManager.getInstance(this)
    }

    override val notificationManager: HabitNotificationManager by lazy {
        HabitNotificationManager(this)
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