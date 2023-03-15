package jp.suji.habit.ui.add.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import jp.suji.habit.ui.core.HabitIcon
import jp.suji.habit.ui.core.HabitIcons

@Composable
fun IconChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    icon: HabitIcon,
    onTap: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onTap,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(all = 0.dp)
    ) {
        Image(
            painter = painterResource(id = icon.res),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,

                )
        )
    }
}

private class IconChipParamProvider: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconChip(@PreviewParameter(IconChipParamProvider::class) param: Boolean) {
    IconChip(
        isSelected = param,
        icon = HabitIcons[0],
        onTap = {}
    )
}