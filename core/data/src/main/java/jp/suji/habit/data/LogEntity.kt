package jp.suji.habit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "log",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("habitId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LogEntity(
    val year: Int,
    val dayOfYear: Int,
    @ColumnInfo(index = true) val habitId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}