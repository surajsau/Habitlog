package jp.suji.habit.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HabitRepositoryTest {

    private lateinit var db: HabitDatabase
    private lateinit var dataRepository: HabitRepository

    @Test
    fun testInsertTask() = runTest {
        dataRepository.insert(title = "title", icon = 1, color = 1)

        dataRepository.getHabits().test {
            val list = awaitItem()

            assertEquals(list.size, 1)
            assertEquals(list[0].logs.size, 0)
            assertEquals(list[0].habit.title, "title")
            assertEquals(list[0].habit.icon, 1)
            assertEquals(list[0].habit.color, 1)
        }
    }

    @Test
    fun testUpdateTitle() = runTest {
        dataRepository.insert(title = "title", icon = 1, color = 1)
        dataRepository.updateTitle(id = 1, title = "changed title")

        dataRepository.getHabits().test {
            awaitItem()
            val latest = awaitItem()

            assertEquals(latest.size, 1)
            assertEquals(latest[0].habit.title, "changed title")
        }
    }

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context = context,
            klass = HabitDatabase::class.java
        ).build()

        dataRepository = HabitRepository(
            habitDao = db.habitDao(),
            logDao = db.logDao()
        )
    }

    @After
    fun tearDown() {
        db.close()
    }
}