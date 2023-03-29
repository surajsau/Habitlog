package jp.suji.habit.ui.settings.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import jp.suji.habit.ui.settings.SettingsScreen
import jp.suji.habit.ui.settings.license.LicenseScreen

const val SettingsRoute = "settings"
const val LicenseRoute = "license"

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

    composable(
        route = LicenseRoute,
        enterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left) },
        exitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Right) }
    ) {
        LicenseScreen(
            modifier = Modifier.fillMaxSize(),
            onTapBack = onTapDismiss
        )
    }
}