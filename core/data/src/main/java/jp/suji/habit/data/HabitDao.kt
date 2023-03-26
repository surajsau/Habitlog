package jp.suji.habit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Transaction
    @Query("SELECT * FROM habit")
    fun watch(): Flow<List<HabitWithLog>>

    @Transaction
    @Query("SELECT * FROM habit")
    suspend fun getAll(): List<HabitWithLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HabitEntity): Long

    @Update
    suspend fun update(entity: HabitEntity)

    @Query("SELECT * FROM habit WHERE id = :id")
    suspend fun get(id: Int): HabitEntity?

    @Query("DELETE FROM habit WHERE id = :id")
    suspend fun delete(id: Int)
}
