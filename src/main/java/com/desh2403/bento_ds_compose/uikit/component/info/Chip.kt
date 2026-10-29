package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Compact element for a filter, a quick choice or a tag.
 *
 * Bento DS: [Chip](https://developer.bento-ds.com/Components/Chip)
 *
 * Differences from Bento DS:
 * - colors follow the selection state instead of the Bento DS `color` palette;
 * - [selected], [onClick] and a custom trailing button are extensions;
 * - there is no `maxCharacters` or `uppercase`: the label is limited to one line.
 *
 * @param label text of the chip. Bento DS `label`.
 * @param modifier modifier of the chip.
 * @param selected highlights the chip with the brand tint.
 * @param onClick called when the chip is clicked; `null` makes the chip non-interactive.
 * @param leadingIcon icon before the label. Bento DS `icon`.
 * @param trailingButtonIcon icon of a button after the label, e.g. a cross to dismiss the chip.
 * @param onTrailingButtonClick called when the trailing button is clicked. Bento DS `onDismiss`.
 * @param trailingButtonLabel accessibility label of the trailing button. Bento DS `dismissButtonLabel`.
 */
@Composable
fun Chip(
    label: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingButtonIcon: Int? = null,
    onTrailingButtonClick: (() -> Unit)? = null,
    trailingButtonLabel: String? = null,
) {
    val shape = BentoDSTheme.shapes.inputShape
    val contentColor = if (selected) BentoDSTheme.colors.text.interactive
    else BentoDSTheme.colors.text.primary
    Row(
        modifier = modifier
            .height(BentoDSTheme.dims.x8 + BentoDSTheme.dims.x1)
            .clip(shape)
            .background(
                color = if (selected) BentoDSTheme.colors.brand.tertiary
                else BentoDSTheme.colors.bg.elevated,
                shape = shape,
            )
            .border(
                width = BentoDSTheme.dims.x0_25,
                color = if (selected) BentoDSTheme.colors.outline.interactive
                else BentoDSTheme.colors.outline.decorative,
                shape = shape,
            )
            .then(
                if (onClick != null) Modifier.selectable(
                    selected = selected,
                    role = Role.Tab,
                    onClick = onClick,
                ) else Modifier
            )
            .padding(horizontal = BentoDSTheme.dims.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.let {
            BentoDSIcon(
                iconSize = IconSize.M,
                iconTint = contentColor,
                iconRes = it,
            )
            HSpace(w = BentoDSTheme.dims.x1 + BentoDSTheme.dims.x0_5)
        }
        Text(
            text = label,
            style = BentoDSTheme.typography.labelMedium,
            color = contentColor,
            maxLines = 1,
        )
        trailingButtonIcon?.let {
            HSpace(w = BentoDSTheme.dims.x1)
            BentoDSIconButton(
                iconRes = it,
                size = IconSize.M,
                buttonType = ButtonType.SECONDARY_TRANSPARENT,
                needPadding = false,
                contentDescription = trailingButtonLabel,
                onClick = onTrailingButtonClick ?: {},
            )
        }
    }
}

@Preview
@Composable
private fun ChipPreview() {
    BentoDSTheme {
        Row {
            Chip(label = "Popular", selected = true, onClick = {})
            HSpace(w = BentoDSTheme.dims.x2)
            Chip(label = "Water", leadingIcon = R.drawable.ph_drop, onClick = {})
            HSpace(w = BentoDSTheme.dims.x2)
            Chip(label = "Tag", trailingButtonIcon = R.drawable.ic_x, onTrailingButtonClick = {})
        }
    }
}
