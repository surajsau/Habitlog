package jp.suji.habit.ui.core.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.LookaheadLayoutScope
import androidx.compose.ui.layout.MeasurePolicy

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SharedTransitionContainer(
    modifier: Modifier = Modifier,
    content: @Composable SharedTransitionScope.() -> Unit
) {
    LookaheadLayout(
        modifier = modifier,
        content = { content(SharedTransitionScope(this)) },
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val width = placeables.maxOf { it.width }
            val height = placeables.maxOf { it.height }
            layout(width, height) {
                placeables.forEach {
                    it.placeRelative(0, 0)
                }
            }
        }
    )
}