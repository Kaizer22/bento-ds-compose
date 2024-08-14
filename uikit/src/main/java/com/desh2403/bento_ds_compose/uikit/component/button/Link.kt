package com.desh2403.bento_ds_compose.uikit.component.button

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

enum class LinkSize {
    LARGE, MEDIUM, SMALL
}

// TODO change color on press
@Composable
fun Link(
    modifier: Modifier = Modifier,
    text: String,
    size: LinkSize = LinkSize.MEDIUM,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier.clickable(onClick = onClick),
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