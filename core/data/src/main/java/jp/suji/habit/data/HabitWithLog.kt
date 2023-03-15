package jp.suji.habit.data

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithLog(
    @Embedded val habit: HabitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val logs: List<LogEntity>
)