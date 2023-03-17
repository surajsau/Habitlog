package jp.suji.habit.notification

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import jp.suji.habit.model.Habit
import jp.suji.habit.ui.core.HabitIcons
import jp.suji.habit.ui.core.R

class HabitNotification(
    private val context: Context,
    private val habit: Habit
) {

    private val actionCompleted: NotificationCompat.Action
        get() = NotificationCompat.Action
            .Builder(
                IconCompat.createWithResource(context, R.drawable.ic_add),
                context.getString(R.string.notification_habit_action_yes),
                MarkHabitCompleteReceiver.createPendingIntent(context, habit.id)
            )
            .build()

    fun create(): Notification {
        return NotificationCompat.Builder(context, HabitNotificationChannelId)
            .setSmallIcon(IconCompat.createWithResource(context, HabitIcons[habit.icon.index].res))
            .setContentTitle(habit.title)
            .setContentText(context.getString(R.string.notification_habit_description))
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText(context.getString(R.string.notification_habit_description_big))
//            )
            .addAction(actionCompleted)
            .build()
    }
}