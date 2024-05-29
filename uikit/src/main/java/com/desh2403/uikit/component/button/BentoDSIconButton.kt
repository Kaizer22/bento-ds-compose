package com.desh2403.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.uikit.theme.BentoDSTheme
import com.desh2403.uikit.R

@Composable
fun BentoDSIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.PRIMARY_SOLID,
    size: ButtonIconSize = ButtonIconSize.XL,
    isNeedPadding: Boolean = true,
    iconTint: Color? = null,
) {
    val buttonSize = mapIconButtonSize(buttonIconSize = size)
    BentoDSButton(
        modifier = modifier,
        onClick = onClick,
        leadingIcon = iconRes,
        buttonSize = buttonSize,
        buttonIconSize = size,
        buttonType = buttonType,
        isGotPadding = isNeedPadding,
        iconsTint = iconTint,
    )
}

@Composable
fun mapIconButtonSize(buttonIconSize: ButtonIconSize) = when(buttonIconSize) {
    ButtonIconSize.S -> ButtonSize.ICON_S
    ButtonIconSize.M -> ButtonSize.ICON_M
    ButtonIconSize.L -> ButtonSize.ICON_L
    ButtonIconSize.XL -> ButtonSize.ICON_XL
}

@Preview(showBackground = true)
@Composable
fun IconButtonPrimarySolidPreview() {
    BentoDSTheme {
        Row {
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {}
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.L,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.M,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.S,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPrimaryOutlinedPreview() {
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
                size = ButtonIconSize.L,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.M,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.S,
                buttonType = ButtonType.PRIMARY_OUTLINED,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPrimaryTransparentPreview() {
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
                size = ButtonIconSize.L,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.M,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
            BentoDSIconButton(
                iconRes = R.drawable.ic_chevron_down,
                onClick = {},
                size = ButtonIconSize.S,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
            )
        }
    }
}