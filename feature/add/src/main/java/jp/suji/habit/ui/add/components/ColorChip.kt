package jp.suji.habit.ui.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.suji.habit.ui.core.HabitColor

@Composable
fun ColorChip(
    modifier: Modifier = Modifier,
    selected: Boolean,
    color: Color,
    onTap: () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                width = 4.dp,
                color = if (selected) MaterialTheme.colorScheme.inversePrimary else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
            .size(36.dp)
            .clickable(onClick = onTap)
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp)
            ),
    )
}

private class ColorChipParamProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconChip(@PreviewParameter(ColorChipParamProvider::class) param: Boolean) {
    ColorChip(
        selected = param,
        color = HabitColor[0],
        onTap = {}
    )
}