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

enum class IconSize(
    val sizeDp: Int,
) {
    XS(8), S(12), M(16), L(24), XL(40)
}

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