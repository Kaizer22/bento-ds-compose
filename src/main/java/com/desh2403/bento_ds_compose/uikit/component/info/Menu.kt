package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

const val MENU_WIDTH_DP = 190
@Composable
fun Menu(
    modifier: Modifier = Modifier,
    header: String? = null,
    items: List<String>,
    onItemClick: ((String) -> Unit)? = null,
) {
    BentoDSCard(
        modifier = modifier.width(MENU_WIDTH_DP.dp),
        paddingValues = PaddingValues(BentoDSTheme.dimensions.x0)
    ) {
        Column {
            header?.let {
                MenuHeader(
                    text = header
                )
            }
            Box(
                modifier = Modifier.padding(BentoDSTheme.dimensions.x2)
            ) {
                Column {
                    items.forEach { item ->
                        MenuItem(
                            text = item,
                            onClick = { onItemClick?.invoke(item) }
                        )
                    }
                }

            }
        }

    }
}

@Composable
fun MenuHeader(
    text: String,
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BentoDSTheme.colors.bg.secondary)
            .padding(BentoDSTheme.dimensions.x6),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BentoDSTheme.typography.bodyMedium,
            color = BentoDSTheme.colors.text.primary,
        )
    }
}

// TODO ListItem
@Composable
fun MenuItem(
    text: String,
    onClick: (() -> Unit)?,
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }
    Box(
        modifier = clickableModifier
            .fillMaxWidth()
            .padding(BentoDSTheme.dimensions.x3),

        ) {
        Text(
            text = text,
            style = BentoDSTheme.typography.bodyMedium,
            color = BentoDSTheme.colors.text.primary,
        )
    }
}

@Composable
@Preview
fun MenuPreview() {
    BentoDSTheme {
        Menu(
            header = "Header",
            items = listOf(
                "Item1",
                "Item2"
            )
        )
    }
}