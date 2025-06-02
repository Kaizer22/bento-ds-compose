package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

// TODO Chip
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    label: String,
    color: Color = BentoDSTheme.colors.visualisation.softRed,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingButtonIcon:  Int? = null,
    onTrailingButtonClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .background(
                color = color,
                shape = BentoDSTheme.shapes.roundShape,
            )
            .padding(
                horizontal = BentoDSTheme.dims.x3,
                vertical = BentoDSTheme.dims.x1
            )
    ) {
        leadingIcon?.let {
            Icon(
                modifier = Modifier.size(
                    BentoDSTheme.dims.x4
                ),
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
            )
        }
        HSpace(w = BentoDSTheme.dims.x1)
        Text(
            text = label,
            style = BentoDSTheme.typography.labelSmall,
            color = BentoDSTheme.colors.text.primary,
        )
        HSpace(w = BentoDSTheme.dims.x2)
        trailingButtonIcon?.let {
            BentoDSIconButton(
                iconRes = trailingButtonIcon,
                size = IconSize.L,
                buttonType = ButtonType.SECONDARY_TRANSPARENT,
                needPadding = false,
                onClick = onTrailingButtonClick ?: {},
            )
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    BentoDSTheme {
        Chip(
            label = "Label",
            leadingIcon = R.drawable.ic_placeholder,
            trailingButtonIcon = R.drawable.ic_x,
        )
    }
}