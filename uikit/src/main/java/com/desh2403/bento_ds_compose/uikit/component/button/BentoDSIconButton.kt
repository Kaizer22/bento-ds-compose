package com.desh2403.bento_ds_compose.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

@Composable
fun BentoDSIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.PRIMARY_SOLID,
    size: IconSize = IconSize.L,
    isNeedPadding: Boolean = true,
    iconTint: Color? = null,
) {
    val buttonSize = when (size) {
        IconSize.XS -> ButtonSize.S
        IconSize.S -> ButtonSize.S
        IconSize.M -> ButtonSize.M
        IconSize.L -> ButtonSize.L
        IconSize.XL -> ButtonSize.L
    }
    val paddings = when (size) {
        IconSize.XS, IconSize.S -> BentoDSTheme.dimensions.x2
        IconSize.M, IconSize.L, IconSize.XL -> BentoDSTheme.dimensions.x4
    }
    BentoDSButton(
        modifier = modifier,
        onClick = onClick,
        leadingIcon = iconRes,
        buttonSize = buttonSize,
        buttonIconSize = size,
        buttonType = buttonType,
        isGotPadding = isNeedPadding,
        paddingValues = PaddingValues(paddings),
        iconsTint = iconTint,
    )
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