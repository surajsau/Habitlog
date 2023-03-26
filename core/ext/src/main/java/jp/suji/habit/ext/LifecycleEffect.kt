package jp.suji.habit.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifecycleEffect(
    key: Any? = null,
    block: (Lifecycle.Event) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(key1 = key ?: Unit) {
        val observer = LifecycleEventObserver { _, event ->
            block(event)
        }.also { lifecycle.addObserver(it) }

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}