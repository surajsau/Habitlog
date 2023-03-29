package jp.suji.habit.ui.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import jp.suji.habit.ui.settings.SettingsScreen

const val SettingsRoute = "settings"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settings(
    onTapDismiss: () -> Unit,
    onTapNotificationSettings: () -> Unit,
    onTapLicense: () -> Unit
) {
    composable(
        route = SettingsRoute,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        SettingsScreen(
            modifier = Modifier.fillMaxSize(),
            onTapDismiss = onTapDismiss,
            onTapNotificationSettings = onTapNotificationSettings,
            onTapLicense = onTapLicense
        )
    }
}