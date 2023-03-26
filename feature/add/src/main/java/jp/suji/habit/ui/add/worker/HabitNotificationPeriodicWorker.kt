package jp.suji.habit.ui.add.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import jp.suji.habit.di.notificationScope
import jp.suji.habit.model.Habit
import jp.suji.habit.ui.add.worker.mark.MarkHabitDoneNotification
import jp.suji.habit.ui.core.HabitIcons
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class HabitNotificationPeriodicWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val habitId = inputData.getInt(KEY_HABIT_ID, -1)
        if (habitId == -1) {
            return Result.success()
        }

        val title = inputData.getString(KEY_TITLE) ?: ""
        val iconRes = inputData.getInt(KEY_ICON_RES, 0)

        with(context.notificationScope) {
            notificationManager.notify(
                MarkHabitDoneNotification(
                    context = context,
                    model = MarkHabitDoneNotification.Model(
                        title = title,
                        habitId = habitId,
                        habitIconRes = iconRes
                    )
                )
            )
        }
        return Result.success()
    }

    companion object {
        private const val KEY_HABIT_ID = "habitid"
        private const val KEY_TITLE = "title"
        private const val KEY_ICON_RES = "iconres"

        fun createRequest(
            hour: Int,
            minute: Int,
            id: Int,
            title: String,
            iconRes: Int
        ): WorkRequest {
            val now = LocalDateTime.now()
            val habitTime = now.withHour(hour).withMinute(minute)

            val initialDelay = when {
                habitTime.isAfter(now) -> Duration.between(now, habitTime)
                else -> Duration.between(habitTime, now)
            }

            return PeriodicWorkRequestBuilder<HabitNotificationPeriodicWorker>(24, TimeUnit.HOURS)
                .setInputData(
                    Data.Builder()
                        .putInt(KEY_HABIT_ID, id)
                        .putString(KEY_TITLE, title)
                        .putInt(KEY_ICON_RES, iconRes)
                        .build()
                )
                .setInitialDelay(initialDelay)
                .build()
        }
    }
}