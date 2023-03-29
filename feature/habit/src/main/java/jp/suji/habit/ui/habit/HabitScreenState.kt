package jp.suji.habit.ui.habit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.domain.CheckHabit
import jp.suji.habit.domain.UncheckHabit
import jp.suji.habit.domain.GetHabits
import jp.suji.habit.model.Habit
import jp.suji.habit.model.HabitId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.onStart
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
            getHabits = context.usecaseScope.watchHabitlog()
        )
    }
}

@Stable
class HabitScreenState(
    private val coroutineScope: CoroutineScope,
    private val getHabits: GetHabits,
    private val checkHabit: CheckHabit,
    private val uncheckHabit: UncheckHabit
) {
    private var loading by mutableStateOf(false)
    private val habits = mutableStateListOf<Habit>()

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    init {
        coroutineScope.launch {
            habits.addAll(getHabits())
        }

        coroutineScope.launch {
            combine(
                snapshotFlow { loading },
                snapshotFlow { habits }
            ) { values ->
                val loading = values[0] as Boolean
                if (loading) {
                    return@combine UiState.Loading
                }

                val habits = values[1] as List<Habit>
                return@combine UiState.Data(habits = habits)
            }.collect { uiState = it }
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

            habits[index] = habit.copy(completedToday = !habit.completedToday)
        }
    }

    sealed interface UiState {
        object Loading: UiState
        data class Data(val habits: List<Habit>):  UiState
    }
}