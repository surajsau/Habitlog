package jp.suji.habit.di

import android.content.Context
import jp.suji.habit.notification.HabitNotificationManager

interface NotificationScope {
    val notificationManager: HabitNotificationManager
}

val Context.notificationScope: NotificationScope
    get() = applicationContext as NotificationScope