package jp.suji.habit.ui.settings.license

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.suji.habit.ui.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseScreen(
    modifier: Modifier = Modifier,
    onTapDismiss: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_license)) },
            navigationIcon = {
                IconButton(onClick = onTapDismiss) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLicenseScreen() {
    LicenseScreen(modifier = Modifier.fillMaxSize()){}
}