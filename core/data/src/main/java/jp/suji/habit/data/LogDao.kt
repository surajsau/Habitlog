package jp.suji.habit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LogEntity)

    @Query("DELETE FROM log WHERE year = :year AND dayOfYear = :dayOfYear AND habitId = :habitId")
    suspend fun delete(habitId: Int, year: Int, dayOfYear: Int)
}