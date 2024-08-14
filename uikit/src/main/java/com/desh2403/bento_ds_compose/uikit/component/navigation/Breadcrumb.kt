package com.desh2403.bento_ds_compose.uikit.component.navigation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.button.Link
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSIcon
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

@Composable
fun Breadcrumb(
    modifier: Modifier = Modifier,
    path: List<String>,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = modifier.horizontalScroll(
            rememberScrollState()
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        path.forEachIndexed { i, item ->
            if (i != path.size - 1) {
                Link(
                    text = item,
                    onClick = { onClick.invoke(item) }
                )
                HSpace(w = BentoDSTheme.dimensions.x4)
                BentoDSIcon(
                    iconSize = IconSize.S,
                    iconRes = R.drawable.ic_chevron_right,
                )
                HSpace(w = BentoDSTheme.dimensions.x4)
            } else {
                Text(
                    text = item,
                    style = BentoDSTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BreadcrumbPreview() {
    BentoDSTheme {
        Breadcrumb(
            path = listOf(
                "Root",
                "1st level",
                "2nd level",
                "3rd level",
                "4th level",
                "Current page",
            ),
            onClick = {},
        )
    }
}