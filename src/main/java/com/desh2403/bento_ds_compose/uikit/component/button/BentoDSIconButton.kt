package com.desh2403.bento_ds_compose.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Button that shows only an icon.
 *
 * Bento DS has no separate page for it: it is a [Button](https://developer.bento-ds.com/Components/Button)
 * without a label. The whole component is an extension of this library.
 *
 * @param modifier modifier of the button.
 * @param iconRes icon of the button.
 * @param onClick called when the button is clicked.
 * @param buttonType hierarchy and kind of the button.
 * @param size size of the icon; the button size and padding follow it.
 * @param needPadding `false` removes the padding around the icon.
 * @param enabled whether the button reacts to clicks.
 * @param iconTint tint of the icon in the enabled state; the [buttonType] color by default.
 * @param contentDescription accessibility label of the button. Set it unless the action is described elsewhere.
 */
@Composable
fun BentoDSIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.PRIMARY_SOLID,
    size: IconSize = IconSize.L,
    needPadding: Boolean = true,
    enabled: Boolean = true,
    iconTint: Color? = null,
    contentDescription: String? = null,
) {
    val buttonSize = when (size) {
        IconSize.XS -> ButtonSize.S
        IconSize.S -> ButtonSize.S
        IconSize.M -> ButtonSize.M
        IconSize.L -> ButtonSize.L
        IconSize.XL -> ButtonSize.L
    }
    val paddings = when (size) {
        IconSize.XS, IconSize.S -> BentoDSTheme.dims.x2
        IconSize.M, IconSize.L, IconSize.XL -> BentoDSTheme.dims.x4
    }
    BentoDSButton(
        modifier = modifier,
        onClick = onClick,
        leadingIcon = iconRes,
        buttonSize = buttonSize,
        buttonIconSize = size,
        buttonType = buttonType,
        needPadding = needPadding,
        paddingValues = PaddingValues(paddings),
        iconsTint = iconTint,
        enabled = enabled,
        contentDescription = contentDescription,
    )
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPrimarySolidPreview() {
    BentoDSTheme {
        Row(verticalAlignment = Alignment.Bottom) {
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {}
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.L,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.M,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.S,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPrimaryOutlinedPreview() {
    BentoDSTheme {
        Row {
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.L,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.M,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.S,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPrimaryTransparentPreview() {
    BentoDSTheme {
        Row {
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.L,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.M,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = IconSize.S,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
        }
    }
}