package jp.suji.habit.ui.habit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.domain.CheckHabit
import jp.suji.habit.domain.UncheckHabit
import jp.suji.habit.domain.WatchHabitlog
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.launch

@Composable
fun rememberHabitScreenState(): HabitScreenState {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    return remember(context, coroutineScope) {
        HabitScreenState(
            coroutineScope = coroutineScope,
            checkHabit = context.usecaseScope.checkHabit(),
            uncheckHabit = context.usecaseScope.uncheckHabit(),
            watchHabitLog = context.usecaseScope.watchHabitlog()
        )
    }
}

@Stable
class HabitScreenState(
    private val coroutineScope: CoroutineScope,
    private val watchHabitLog: WatchHabitlog,
    private val checkHabit: CheckHabit,
    private val uncheckHabit: UncheckHabit
) {
    val habits = mutableStateListOf<Habit>()

    init {
        coroutineScope.launch {
            watchHabitLog()
                .filterNot { it.isEmpty() }
                .collect {
                    habits.clear()
                    habits.addAll(it)
                }
        }
    }

    fun onTapComplete(id: HabitId) {
        coroutineScope.launch {
            val index = habits.indexOfFirst { it.id == id }
            if (index == -1)
                return@launch

            val habit = habits[index]
            if (habit.completedToday) {
                uncheckHabit(id = habit.id)
            } else {
                checkHabit(id = habit.id)
            }
        }
    }

    sealed interface UiState {
        object Loading: UiState

        data class Data(
            val habits: List<Habit>
        )
    }
}