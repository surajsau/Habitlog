package jp.suji.habit.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberSettingsScreenState(): SettingsScreenState {
    return remember {
        SettingsScreenState()
    }
}

@Stable
class SettingsScreenState {

}