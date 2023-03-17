package jp.suji.habit.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import jp.suji.habit.model.HabitId

private const val ACTION_NAME = "mark-habit-complete"
const val KEY_HABIT_ID = "id"

class MarkHabitCompleteReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val habitId = intent.getIntExtra(KEY_HABIT_ID, -1)
        if (habitId == -1)
            return

        WorkManager.getInstance(context)
            .enqueue(
                OneTimeWorkRequestBuilder<MarkCompleteWorker>()
                    .setInputData(
                        Data.Builder()
                            .putInt(KEY_HABIT_ID, habitId)
                            .build()
                    )
                    .build()
            )
    }

    companion object {
        fun createPendingIntent(
            context: Context,
            habitId: HabitId
        ): PendingIntent {
            val intent = Intent(ACTION_NAME).apply {
                putExtra(KEY_HABIT_ID, habitId.value)
            }

            return PendingIntent.getBroadcast(context, habitId.value, intent, PendingIntent.FLAG_IMMUTABLE)
        }
    }
}