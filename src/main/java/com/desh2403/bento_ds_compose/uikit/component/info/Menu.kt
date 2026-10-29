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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Width of a [Menu] in dp. */
const val MENU_WIDTH_DP = 190

/**
 * Static list of text items on a card, with an optional header.
 *
 * Bento DS: [Menu](https://developer.bento-ds.com/Components/Menu)
 *
 * Differences from Bento DS: the menu is always visible and has no trigger, placement, nested menus,
 * icons or dividers; open it in a popup or a dropdown yourself.
 *
 * @param modifier modifier of the menu.
 * @param header text above the items. Bento DS `header`.
 * @param items labels of the items. Bento DS `items`.
 * @param onItemClick called with the label of the clicked item; `null` makes the items non-interactive.
 */
@Composable
fun Menu(
    modifier: Modifier = Modifier,
    header: String? = null,
    items: List<String>,
    onItemClick: ((String) -> Unit)? = null,
) {
    BentoDSCard(
        modifier = modifier
            .padding(BentoDSTheme.dims.x3)
            .width(MENU_WIDTH_DP.dp),
        paddingValues = PaddingValues(BentoDSTheme.dims.x0)
    ) {
        Column {
            header?.let {
                MenuHeader(
                    text = header
                )
            }
            Box(
                modifier = Modifier.padding(BentoDSTheme.dims.x2)
            ) {
                Column {
                    items.forEach { item ->
                        MenuItem(
                            text = item,
                            onClick = onItemClick?.let { { it(item) } },
                        )
                    }
                }

            }
        }

    }
}

/** Header of a [Menu]. */
@Composable
fun MenuHeader(
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BentoDSTheme.colors.bg.secondary)
            .padding(BentoDSTheme.dims.x6),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = BentoDSTheme.typography.bodyMedium,
            color = BentoDSTheme.colors.text.primary,
        )
    }
}

/** Item of a [Menu]; `null` [onClick] makes it non-interactive. */
@Composable
fun MenuItem(
    text: String,
    onClick: (() -> Unit)?,
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(role = Role.Button, onClick = onClick)
    } else {
        Modifier
    }
    Box(
        modifier = clickableModifier
            .fillMaxWidth()
            .padding(BentoDSTheme.dims.x3),
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
private fun MenuPreview() {
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