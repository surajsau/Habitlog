package jp.suji.habit.ui.core.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round

context(SharedTransitionScope)
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.animateConstraintsModifier() = composed {

    var offsetAnimation: Animatable<IntOffset, AnimationVector2D>? by remember { mutableStateOf(null) }
    var sizeAnimation: Animatable<IntSize, AnimationVector2D>? by remember { mutableStateOf(null) }

    var currentOffset: IntOffset by remember { mutableStateOf(IntOffset.Zero) }
    var targetOffset: IntOffset? by remember { mutableStateOf(null) }

    var targetSize: IntSize? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        snapshotFlow { targetOffset }
            .collect { target ->
                if (target != null && target != offsetAnimation?.targetValue) {
                    offsetAnimation?.run {
                        animateTo(targetValue = target)
                    } ?: Animatable(target, IntOffset.VectorConverter).let {
                        offsetAnimation = it
                    }
                }
            }

        snapshotFlow { targetSize }
            .collect { target ->
                if (target != null && target != sizeAnimation?.targetValue) {
                    sizeAnimation?.run {
                        animateTo(targetValue = target)
                    } ?: Animatable(target, IntSize.VectorConverter).let {
                        sizeAnimation = it
                    }
                }
            }
    }

    Modifier
        .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
            targetOffset = lookaheadScopeCoordinates
                .localLookaheadPositionOf(layoutCoordinates)
                .round()
            currentOffset = lookaheadScopeCoordinates
                .localPositionOf(layoutCoordinates, Offset.Zero)
                .round()
        }
        .intermediateLayout { measurable, constraints, lookaheadSize ->
            targetSize = lookaheadSize

            val (width, height) = sizeAnimation?.value ?: lookaheadSize
            val animatedConstraints = Constraints.fixed(width, height)
            val placeable = measurable.measure(animatedConstraints)

            layout(placeable.width, placeable.height) {
                val (x, y) = offsetAnimation?.run { value - currentOffset } ?: (targetOffset!! - currentOffset)
                placeable.place(x, y)
            }
        }
}