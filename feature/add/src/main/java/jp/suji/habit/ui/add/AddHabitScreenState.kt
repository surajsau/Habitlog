package jp.suji.habit.ui.add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.domain.AddHabit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberAddHabitScreenState(): AddHabitScreenState {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0
    )

    return remember(coroutineScope, context) {
        AddHabitScreenState(
            coroutineScope = coroutineScope,
            addHabit = context.usecaseScope.addHabit(),
            timePickerState = timePickerState
        )
    }
}

@Stable
class AddHabitScreenState(
    private val coroutineScope: CoroutineScope,
    private val addHabit: AddHabit,
    val timePickerState: TimePickerState
) {
    private var title = ""
    private var description = ""
    private var selectedIconIndex = 0
    private var selectedColorIndex = 0
    private var enableNotification = false
    private var notificationMinute = 0
    private var notificationHour = 0

    var showTimePIcker by mutableStateOf(false)
        private set

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
                color = uiState.selectedColorIndex,
                enableNotification = enableNotification,
                notificationHour = notificationHour,
                notificationMinute = notificationMinute
            )

            dismiss = true
        }
    }

    fun onChangeNotificationEnabled(enabled: Boolean) {
        enableNotification = enabled
        uiState = uiState.copy(notificationEnabled = enabled)
    }

    fun onTapNotificationTime() {
        if (!showTimePIcker)
            showTimePIcker = true
    }

    fun onRequestDismissTimePicker() {
        showTimePIcker = false
    }

    fun onTapTimePickerDismiss() {
        showTimePIcker = false
    }

    fun onTapTimePickerConfirm() {
        showTimePIcker = false
        notificationHour = timePickerState.hour
        notificationMinute = timePickerState.minute
        uiState = uiState.copy(
            notificationTime = timePickerState.displayValue
        )
    }

    data class UiState(
        val title: String = "",
        val description: String = "",
        val selectedColorIndex: Int = 0,
        val selectedIconIndex: Int = 0,
        val notificationEnabled: Boolean = false,
        val notificationTime: String = "12:00"
    )
}

context(AddHabitScreenState)
val TimePickerState.displayValue: String
    get() {
        val hourValue = "${if(hour < 10) "0" else ""}$hour"
        val minuteValue = "${if(minute < 10) "0" else ""}$minute"
        return "$hourValue:$minuteValue"
    }