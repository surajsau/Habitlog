package jp.suji.habit.ui.core

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import jp.suji.habit.ui.core.theme.DarkColorScheme
import jp.suji.habit.ui.core.theme.LightColorScheme
import jp.suji.habit.ui.theme.Typography

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val isDarkTheme = isSystemInDarkTheme()
    val isDynamicThemEnabled =
        dynamicColor && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)

    val colorScheme = when {
        isDarkTheme && isDynamicThemEnabled -> dynamicDarkColorScheme(context = context)
        isDarkTheme -> DarkColorScheme
        isDynamicThemEnabled -> dynamicLightColorScheme(context = context)
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}