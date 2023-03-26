package jp.suji.habit.data

import jp.suji.habit.exception.HabitNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HabitRepository(
    private val habitDao: HabitDao,
    private val logDao: LogDao
) {

    suspend fun insert(title: String, icon: Int, color: Int): Int = withContext(Dispatchers.IO) {
        val entity = HabitEntity(
            title = title,
            icon = icon,
            color = color
        )
        return@withContext habitDao.insert(entity = entity).toInt()
    }

    fun watch(): Flow<List<HabitWithLog>> = habitDao.watch()

    suspend fun updateColor(id: Int, color: Int): HabitEntity = withContext(Dispatchers.IO) {
        val entity = getForId(id = id)
        val updatedEntity = entity.copy(color = color)
        habitDao.update(entity = updatedEntity)
        return@withContext updatedEntity
    }

    suspend fun updateTitle(id: Int, title: String): HabitEntity = withContext(Dispatchers.IO) {
        val entity = getForId(id = id)
        val updatedEntity = entity.copy(title = title)
        habitDao.update(entity = updatedEntity)
        return@withContext updatedEntity
    }

    suspend fun addDay(id: Int, dayOfYear: Int, year: Int) = withContext(Dispatchers.IO) {
        val entity = LogEntity(year = year, dayOfYear = dayOfYear, habitId = id)
        logDao.insert(entity = entity)
    }

    suspend fun removeLog(id: Int, dayOfYear: Int, year: Int) = withContext(Dispatchers.IO) {
        logDao.delete(habitId = id, year = year, dayOfYear = dayOfYear)
    }

    suspend fun remove(id: Int) = withContext(Dispatchers.IO) {
        habitDao.delete(id = id)
    }

    private suspend fun getForId(id: Int): HabitEntity = habitDao.get(id = id) ?: throw HabitNotFoundException(id = id)
}