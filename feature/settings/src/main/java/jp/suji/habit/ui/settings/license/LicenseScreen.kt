package jp.suji.habit.ui.settings.license

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import jp.suji.habit.ui.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseScreen(
    modifier: Modifier = Modifier,
    onTapBack: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.title_license)) },
            navigationIcon = {
                IconButton(onClick = onTapBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }
            }
        )

        LibrariesContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = LibraryDefaults.libraryColors(
                backgroundColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                badgeBackgroundColor = MaterialTheme.colorScheme.primary,
                badgeContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            onLibraryClick = {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(it.website)
                    }
                )
            }
        )
    }
}