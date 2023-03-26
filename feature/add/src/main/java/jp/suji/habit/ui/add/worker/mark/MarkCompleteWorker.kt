package jp.suji.habit.ui.add.worker.mark

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.model.HabitId

@Suppress("unused")
class MarkCompleteWorker(private val context: Context, params: WorkerParameters): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val habitId = inputData.getInt(KEY_HABIT_ID, -1)
        if (habitId == -1) {
            return Result.success()
        }

        val checkHabit = context.usecaseScope.checkHabit()
        checkHabit(HabitId(value = habitId))
        return Result.success()
    }

    companion object {
        private const val KEY_HABIT_ID = "worker_habit_id"

        fun request(habitId: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MarkCompleteWorker>()
                .setInputData(
                    Data.Builder()
                        .putInt(KEY_HABIT_ID, habitId)
                        .build()
                )
                .build()
        }
    }
}