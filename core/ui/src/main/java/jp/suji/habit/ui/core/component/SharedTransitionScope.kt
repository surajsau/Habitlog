package jp.suji.habit.ui.core.component

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.LookaheadLayoutScope

@OptIn(ExperimentalComposeUiApi::class)
class SharedTransitionScope constructor(
    private val scope: LookaheadLayoutScope
): LookaheadLayoutScope by scope