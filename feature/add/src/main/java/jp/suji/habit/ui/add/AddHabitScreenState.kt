package jp.suji.habit.ui.add

import android.Manifest
import android.content.Context
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.work.WorkManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import jp.suji.habit.di.usecaseScope
import jp.suji.habit.domain.AddHabit
import jp.suji.habit.ui.add.worker.HabitNotificationPeriodicWorker
import jp.suji.habit.ui.core.HabitIcons
import jp.suji.habit.ui.core.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun rememberAddHabitScreenState(): AddHabitScreenState {
    val postNotificationPermissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0
    )

    return remember(coroutineScope, context, postNotificationPermissionState) {
        AddHabitScreenState(
            context = context,
            coroutineScope = coroutineScope,
            addHabit = context.usecaseScope.addHabit(),
            timePickerState = timePickerState,
            postNotificationPermissionState = postNotificationPermissionState,
            workManager = WorkManager.getInstance(context)
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Stable
class AddHabitScreenState constructor(
    private val context: Context,
    private val coroutineScope: CoroutineScope,
    private val addHabit: AddHabit,
    val timePickerState: TimePickerState,
    private val postNotificationPermissionState: PermissionState,
    private val workManager: WorkManager
) {
    private var title = ""
    private var description = ""
    private var selectedIconIndex = 0
    private var selectedColorIndex = 0
    private var enableNotification = false
    private var notificationMinute = -1
    private var notificationHour = -1

    init {
        coroutineScope.launch {
            snapshotFlow { postNotificationPermissionState.status }
                .collect { status ->
                    when {
                        enableNotification && status.isGranted -> {
                            uiState = uiState.copy(
                                notificationEnabled = true,
                                permissionRationale = null
                            )
                        }

                        status.shouldShowRationale -> {
                            uiState = uiState.copy(
                                permissionRationale = context.getString(R.string.rationale_notification_permission)
                            )
                        }
                    }
                }
        }
    }

    var permissionRationale: String? by mutableStateOf(null)
        private set

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
            val id = addHabit(
                title = title,
                icon = selectedIconIndex,
                color = uiState.selectedColorIndex
            )

            // fire up periodic notification worker
            if (enableNotification) {
                workManager.enqueue(
                    HabitNotificationPeriodicWorker.createRequest(
                        hour = notificationHour,
                        minute = notificationMinute,
                        title = title,
                        id = id,
                        iconRes = HabitIcons[selectedIconIndex].res
                    )
                )
            }

            dismiss = true
        }
    }

    fun onChangeNotificationEnabled(enabled: Boolean) {
        enableNotification = enabled
        if (enabled && !postNotificationPermissionState.status.isGranted) {
            postNotificationPermissionState.launchPermissionRequest()
            return
        }
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
        val notificationTime: String = "12:00",
        val permissionRationale: String? = null
    )
}

context(AddHabitScreenState)
val TimePickerState.displayValue: String
    get() {
        val hourValue = "${if(hour < 10) "0" else ""}$hour"
        val minuteValue = "${if(minute < 10) "0" else ""}$minute"
        return "$hourValue:$minuteValue"
    }