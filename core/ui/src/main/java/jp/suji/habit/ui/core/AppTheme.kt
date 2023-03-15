package jp.suji.habit.ui.core

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val isDarkTheme = isSystemInDarkTheme()
    val isDynamicThemEnabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        isDarkTheme && isDynamicThemEnabled -> dynamicDarkColorScheme(context = context)
        isDarkTheme -> darkColorScheme()
        isDynamicThemEnabled -> dynamicLightColorScheme(context = context)
        else -> lightColorScheme()
    }

    MaterialTheme(
        content = content,
        colorScheme = colorScheme
    )
}