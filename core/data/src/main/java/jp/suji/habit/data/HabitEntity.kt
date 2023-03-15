package jp.suji.habit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit")
data class HabitEntity(
    val title: String,
    val icon: Int,
    val color: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}