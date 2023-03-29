package jp.suji.habit.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberSettingsScreenState(): SettingsScreenState {
    return remember {
        SettingsScreenState()
    }
}

@Stable
class SettingsScreenState {

    var changeThemeDialogShown by mutableStateOf(false)
        private set

    fun onTapChangeTheme() {
        changeThemeDialogShown = true
    }

    fun onDismissChangeThemeDialog() {
        changeThemeDialogShown = false
    }
}