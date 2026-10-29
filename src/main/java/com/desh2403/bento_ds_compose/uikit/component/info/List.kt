package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.input.BentoDSSwitch
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Size of a [BentoDSListItem]: Bento DS `size`. */
enum class ListItemSize {
    /** 40 dp single-line item. */
    MEDIUM,

    /** 48 dp single-line item, 62 dp two-line item. */
    LARGE,
}

/**
 * List item with a leading icon, one or two lines of text and a trailing slot.
 * Variants: single-line ([title] only), two-line ([supportingText] under the title)
 * and overline ([overline] above the title).
 *
 * Bento DS: [List](https://developer.bento-ds.com/Components/List). Bento DS takes a list of `items`;
 * here each item is a separate composable, so any layout can hold them.
 *
 * Extensions over Bento DS: [selected], [destructive] and the [trailing] slot.
 *
 * @param title text of the item.
 * @param modifier modifier of the item.
 * @param supportingText second line under the title.
 * @param overline small uppercase line above the title.
 * @param leadingIcon icon before the text.
 * @param size size of the item. Bento DS `size`.
 * @param selected highlights the item with the brand tint.
 * @param enabled whether the item reacts to clicks; a disabled item uses the disabled colors.
 * @param destructive shows the title and the icon in the negative color, e.g. for "Delete all data".
 * @param onClick called when the item is clicked; `null` makes the item non-interactive, e.g. when
 * a [com.desh2403.bento_ds_compose.uikit.component.input.BentoDSSwitch] in [trailing] handles the clicks.
 * @param trailing content after the text: [ListItemChevron], [ListItemValue], a switch and so on.
 */
@Composable
fun BentoDSListItem(
    title: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    overline: String? = null,
    @DrawableRes leadingIcon: Int? = null,
    size: ListItemSize = ListItemSize.LARGE,
    selected: Boolean = false,
    enabled: Boolean = true,
    destructive: Boolean = false,
    onClick: (() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val background = when {
        selected -> BentoDSTheme.colors.brand.tertiary
        isPressed -> BentoDSTheme.colors.bg.secondary
        else -> Color.Transparent
    }
    val titleColor = when {
        !enabled -> BentoDSTheme.colors.text.disabled
        destructive -> BentoDSTheme.colors.text.negative
        else -> BentoDSTheme.colors.text.primary
    }
    val secondaryColor = if (enabled) BentoDSTheme.colors.text.secondary
    else BentoDSTheme.colors.text.disabled
    val iconColor = when {
        !enabled -> BentoDSTheme.colors.fg.disabled
        destructive -> BentoDSTheme.colors.fg.negative
        else -> BentoDSTheme.colors.fg.primary
    }
    val shape = BentoDSTheme.shapes.inputShape
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(background, shape)
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                ) else Modifier
            )
            .padding(
                if (size == ListItemSize.LARGE) BentoDSTheme.dims.x3 else BentoDSTheme.dims.x2
            ),
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.let {
            BentoDSIcon(iconRes = it, iconSize = IconSize.L, iconTint = iconColor)
        }
        Column(modifier = Modifier.weight(1f)) {
            overline?.let {
                Text(
                    text = it.uppercase(),
                    style = BentoDSTheme.typography.labelSmall,
                    color = secondaryColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = title,
                style = BentoDSTheme.typography.bodyMedium,
                color = titleColor,
            )
            supportingText?.let {
                Text(
                    text = it,
                    style = BentoDSTheme.typography.bodySmall,
                    color = secondaryColor,
                )
            }
        }
        trailing?.invoke(this)
    }
}

/** Chevron for the [BentoDSListItem] trailing slot, showing that the item opens another screen. */
@Composable
fun ListItemChevron(enabled: Boolean = true) {
    BentoDSIcon(
        iconRes = R.drawable.ic_chevron_right,
        iconSize = IconSize.L,
        iconTint = if (enabled) BentoDSTheme.colors.fg.secondary
        else BentoDSTheme.colors.fg.disabled,
    )
}

/** Current value of a setting and a chevron, for the [BentoDSListItem] trailing slot. */
@Composable
fun ListItemValue(value: String, enabled: Boolean = true) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = value,
            style = BentoDSTheme.typography.bodyMedium,
            color = if (enabled) BentoDSTheme.colors.text.secondary
            else BentoDSTheme.colors.text.disabled,
            maxLines = 1,
        )
        ListItemChevron(enabled)
    }
}

/** Header of a group of [BentoDSListItem]s. */
@Composable
fun BentoDSListHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = BentoDSTheme.dims.x3,
                end = BentoDSTheme.dims.x3,
                top = BentoDSTheme.dims.x4,
                bottom = BentoDSTheme.dims.x1,
            ),
        text = text,
        style = BentoDSTheme.typography.labelSmall,
        color = BentoDSTheme.colors.text.secondary,
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun ListItemPreview() {
    BentoDSTheme {
        Column(modifier = Modifier.padding(BentoDSTheme.dims.x1)) {
            BentoDSListHeader(text = "Data")
            BentoDSListItem(
                title = "Language",
                leadingIcon = R.drawable.ic_chats_circle_fill,
                onClick = {},
            ) { ListItemValue("English") }
            BentoDSListItem(
                title = "Reminder",
                supportingText = "Every day at 21:30",
                leadingIcon = R.drawable.ic_bell,
            ) { BentoDSSwitch(checked = true, onCheckedChange = {}) }
            BentoDSListItem(
                title = "Export",
                supportingText = "Diary as PDF",
                leadingIcon = R.drawable.ic_file_arrow_down,
                selected = true,
                onClick = {},
            ) { ListItemChevron() }
            BentoDSListItem(
                title = "First line",
                overline = "Overline",
                size = ListItemSize.MEDIUM,
                enabled = false,
                onClick = {},
            )
            BentoDSListItem(
                title = "Delete all data",
                leadingIcon = R.drawable.ic_bin,
                destructive = true,
                onClick = {},
            )
        }
    }
}
