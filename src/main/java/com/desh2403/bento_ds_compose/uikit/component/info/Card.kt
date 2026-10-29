package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Surface with rounded corners and a small elevation that groups related content.
 *
 * Bento DS: [Card](https://developer.bento-ds.com/Components/Card)
 *
 * Differences from Bento DS:
 * - elevation and corner radius come from the theme instead of `elevation` and `borderRadius`;
 * - [onClick] is an extension that makes the whole card clickable.
 *
 * @param modifier modifier of the card.
 * @param paddingValues padding around [content]. Bento DS `padding*`.
 * @param color background of the card. Bento DS `background`.
 * @param onClick called when the card is clicked; `null` makes the card non-interactive.
 * @param interactionSource interaction source of a clickable card, e.g. to remove the ripple.
 * @param content content of the card.
 */
@Composable
fun BentoDSCard(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(BentoDSTheme.dims.x6),
    color: Color = BentoDSTheme.colors.bg.primary,
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val shape = BentoDSTheme.shapes.cardShape
    val elevation = CardDefaults.cardElevation(defaultElevation = BentoDSTheme.dims.elevationSmall)
    val colors = CardDefaults.cardColors(containerColor = color)
    if (onClick != null) {
        Card(
            modifier = modifier,
            shape = shape,
            elevation = elevation,
            onClick = onClick,
            colors = colors,
            interactionSource = interactionSource,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) { content() }
        }
    } else {
        Card(
            modifier = modifier,
            shape = shape,
            elevation = elevation,
            colors = colors,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) { content() }
        }
    }
}
