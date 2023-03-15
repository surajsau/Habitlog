package jp.suji.habit.ui.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import jp.suji.habit.di.dataScope
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.domain.AddHabit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberAddHabitScreenState(): AddHabitScreenState {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    return remember(coroutineScope, context) {
        AddHabitScreenState(
            coroutineScope = coroutineScope,
            addHabit = context.usecaseScope.addHabit()
        )
    }
}

@Stable
class AddHabitScreenState(
    private val coroutineScope: CoroutineScope,
    private val addHabit: AddHabit
) {
    private var title = ""
    private var description = ""
    private var selectedIconIndex = 0
    private var selectedColorIndex = 0

    var uiState by mutableStateOf(UiState())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var dismiss by mutableStateOf(false)

    fun onChangeTitle(text: String) {
        title = text
        uiState = uiState.copy(title = text)
    }

    fun onChangeDescription(text: String) {
        description = text
        uiState = uiState.copy(description = text)
    }

    fun onTapColor(index: Int) {
        selectedColorIndex = index
        uiState = uiState.copy(selectedColorIndex = index)
    }

    fun onTapIcon(index: Int) {
        selectedIconIndex = index
        uiState = uiState.copy(selectedIconIndex = index)
    }

    fun onTapAdd() {
        if (title.isEmpty()) {
            error = "Please enter title for the task"
            return
        }

        coroutineScope.launch {
            addHabit(
                title = title,
                icon = selectedIconIndex,
                color = uiState.selectedColorIndex
            )

            dismiss = true
        }
    }

    data class UiState(
        val title: String = "",
        val description: String = "",
        val selectedColorIndex: Int = 0,
        val selectedIconIndex: Int = 0
    )
}