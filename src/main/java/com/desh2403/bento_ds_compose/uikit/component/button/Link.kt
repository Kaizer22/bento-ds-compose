package com.desh2403.bento_ds_compose.uikit.component.button

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Text size of a [Link]. */
enum class LinkSize {
    LARGE, MEDIUM, SMALL
}

/**
 * Underlined text that acts as a link.
 *
 * Bento DS has no separate page for it; the link styles are described in
 * [Typography](https://developer.bento-ds.com/Guides/typography). The component is an extension of this library.
 *
 * @param modifier modifier of the link.
 * @param text text of the link.
 * @param size text size of the link.
 * @param isEnabled whether the link reacts to clicks; a disabled link uses the disabled link color.
 * @param onClick called when the link is clicked.
 */
@Composable
fun Link(
    modifier: Modifier = Modifier,
    text: String,
    size: LinkSize = LinkSize.MEDIUM,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier.clickable(enabled = isEnabled, role = Role.Button, onClick = onClick),
        text = text,
        color = if (isEnabled) BentoDSTheme.colors.link.enabled
        else BentoDSTheme.colors.link.disabled,
        style = when (size) {
            LinkSize.LARGE -> BentoDSTheme.typography.linkLarge
            LinkSize.MEDIUM -> BentoDSTheme.typography.linkMedium
            LinkSize.SMALL -> BentoDSTheme.typography.linkSmall
        },
        textDecoration = TextDecoration.Underline,
    )
}