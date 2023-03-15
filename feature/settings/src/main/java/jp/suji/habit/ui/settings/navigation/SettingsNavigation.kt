package jp.suji.habit.ui.settings.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.suji.habit.ui.settings.SettingsScreen

const val SettingsRoute = "settings"

fun NavGraphBuilder.settings(
    onTapDismiss: () -> Unit
) {
    composable(route = SettingsRoute) {
        SettingsScreen(
            modifier = Modifier.fillMaxSize(),
            onTapDismiss = onTapDismiss
        )
    }
}