package jp.suji.habit.notification

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class HabitNotificationManager(private val context: Context) {

    private val manager: NotificationManagerCompat
        get() {
            return NotificationManagerCompat.from(context)
                .apply {
                    val habitNotificationChannel = NotificationChannelCompat.Builder(
                        HabitNotificationChannelId,
                        NotificationManagerCompat.IMPORTANCE_DEFAULT
                    )
                        .setName(context.getString(R.string.channel_habit_notification_name))
                        .setDescription(context.getString(R.string.channel_habit_notification_description))
                        .setVibrationEnabled(true)
                        .build()

                    createNotificationChannel(habitNotificationChannel)
                }
        }

    @Suppress("missingpermission")
    fun notify(notification: HabitNotification) {
        manager.notify(notification.id, notification.create())
    }
}