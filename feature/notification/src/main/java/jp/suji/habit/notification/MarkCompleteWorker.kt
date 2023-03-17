package jp.suji.habit.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.model.HabitId

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
}