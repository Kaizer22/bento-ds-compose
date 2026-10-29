package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/** Icon size in dp: 8, 12, 16, 24 and 40. */
enum class IconSize(
    val sizeDp: Int,
) {
    XS(8), S(12), M(16), L(24), XL(40)
}

/**
 * Icon from a drawable resource, sized by [IconSize].
 *
 * Bento DS: [Icons](https://developer.bento-ds.com/Guides/icons)
 *
 * @param modifier modifier of the icon.
 * @param iconSize size of the icon.
 * @param iconTint tint of the icon; the current content color by default.
 * @param iconRes drawable of the icon.
 * @param contentDescription accessibility label; `null` for decorative icons.
 */
@Composable
fun BentoDSIcon(
    modifier: Modifier = Modifier,
    iconSize: IconSize,
    iconTint: Color = LocalContentColor.current,
    @DrawableRes iconRes: Int,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier.size(iconSize.sizeDp.dp),
        tint = iconTint,
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription,
    )
}