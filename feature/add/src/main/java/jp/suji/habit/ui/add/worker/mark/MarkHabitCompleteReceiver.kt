package jp.suji.habit.ui.add.worker.mark

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import jp.suji.habit.model.HabitId

class MarkHabitCompleteReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val habitId = intent.getIntExtra(KEY_HABIT_ID, -1)
        if (habitId == -1)
            return

        WorkManager.getInstance(context)
            .enqueue(MarkCompleteWorker.request(habitId = habitId))
    }

    companion object {
        const val ACTION_NAME = "mark-habit-complete"
        private const val KEY_HABIT_ID = "id"

        fun createPendingIntent(
            context: Context,
            habitId: Int
        ): PendingIntent {
            val intent = Intent(ACTION_NAME).apply {
                putExtra(KEY_HABIT_ID, habitId)
            }

            return PendingIntent.getBroadcast(
                context,
                habitId,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
            )
        }
    }
}