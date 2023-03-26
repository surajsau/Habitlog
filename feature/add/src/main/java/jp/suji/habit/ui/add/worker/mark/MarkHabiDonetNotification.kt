package jp.suji.habit.ui.add.worker.mark

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import jp.suji.habit.notification.HabitNotification
import jp.suji.habit.notification.HabitNotificationChannelId
import jp.suji.habit.ui.core.R

class MarkHabitDoneNotification(
    private val context: Context,
    private val model: Model
): HabitNotification {

    override val id: Int
        get() = model.habitId

    private val actionCompleted: NotificationCompat.Action
        get() = NotificationCompat.Action
            .Builder(
                IconCompat.createWithResource(context, R.drawable.ic_add),
                context.getString(R.string.notification_habit_action_yes),
                MarkHabitCompleteReceiver.createPendingIntent(context, model.habitId)
            )
            .setAuthenticationRequired(true)
            .build()

    override fun create(): Notification {
        return NotificationCompat.Builder(context, HabitNotificationChannelId)
            .setSmallIcon(IconCompat.createWithResource(context, model.habitIconRes))
            .setContentTitle(model.title)
            .setContentText(context.getString(R.string.notification_habit_description))
            .addAction(actionCompleted)
            .build()
    }

    data class Model(
        val title: String,
        val habitId: Int,
        val habitIconRes: Int
    )
}