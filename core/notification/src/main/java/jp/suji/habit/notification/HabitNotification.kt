package jp.suji.habit.notification

import android.app.Notification

interface HabitNotification {
    val id: Int
    fun create(): Notification
}