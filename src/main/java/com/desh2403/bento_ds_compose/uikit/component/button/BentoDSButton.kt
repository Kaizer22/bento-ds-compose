package com.desh2403.bento_ds_compose.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSIcon
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.theme.PAButtonPalette

/**
 * Button style: hierarchy (primary, secondary, negative, positive, warning) combined with
 * kind (solid, outlined, transparent).
 *
 * Bento DS defines the `primary`, `secondary` and `danger` hierarchies; `NEGATIVE_*` maps to `danger`.
 * The `POSITIVE_*` and `WARNING_*` hierarchies are extensions of this library.
 */
enum class ButtonType {
    PRIMARY_SOLID, PRIMARY_OUTLINED, PRIMARY_TRANSPARENT,
    SECONDARY_SOLID, SECONDARY_OUTLINED, SECONDARY_TRANSPARENT,
    NEGATIVE_SOLID, NEGATIVE_OUTLINED, NEGATIVE_TRANSPARENT,
    POSITIVE_SOLID, POSITIVE_OUTLINED, POSITIVE_TRANSPARENT,
    WARNING_SOLID, WARNING_OUTLINED, WARNING_TRANSPARENT,
}

/** Button size: Bento DS `small`, `medium` and `large`. */
enum class ButtonSize {
    S, M, L,
}

private val OUTLINED_BUTTON_BORDER_WIDTH = 1.dp

/**
 * Button with an optional label and leading and trailing icons.
 * While pressed, the button switches to the `hover` colors of its [buttonType].
 *
 * Bento DS: [Button](https://developer.bento-ds.com/Components/Button)
 *
 * Extensions over Bento DS:
 * - leading and trailing icons can be shown at the same time;
 * - [text] is optional, which gives an icon-only button (see also [BentoDSIconButton]);
 * - positive and warning hierarchies, see [ButtonType];
 * - [fillMaxWidth], [paddingValues], [needPadding], [buttonIconSize] and [iconsTint] layout overrides.
 *
 * @param modifier modifier of the button.
 * @param needPadding `false` removes the content padding, e.g. for an icon button inside a dense row.
 * @param fillMaxWidth whether the button takes the full available width.
 * @param buttonType hierarchy and kind of the button. Bento DS `hierarchy` and `kind`.
 * @param buttonSize size of the button. Bento DS `size`.
 * @param buttonIconSize size of the icons.
 * @param enabled whether the button reacts to clicks. Bento DS `isDisabled`, inverted.
 * @param text label of the button. Bento DS `label`.
 * @param leadingIcon icon before the label. Bento DS `icon` with `iconPosition = "leading"`.
 * @param trailingIcon icon after the label. Bento DS `icon` with `iconPosition = "trailing"`.
 * @param iconsTint tint of the icons and the label in the enabled state; the [buttonType] color by default.
 * @param contentDescription accessibility label of the icons; set it for an icon-only button.
 * @param onClick called when the button is clicked. Bento DS `onPress`.
 * @param paddingValues content padding; by default it depends on [buttonSize].
 */
@Composable
fun BentoDSButton(
    modifier: Modifier = Modifier,
    needPadding: Boolean = true,
    fillMaxWidth: Boolean = false,
    buttonType: ButtonType = ButtonType.PRIMARY_SOLID,
    buttonSize: ButtonSize = ButtonSize.M,
    buttonIconSize: IconSize = IconSize.L,
    enabled: Boolean = true,
    text: String? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    iconsTint: Color? = null,
    contentDescription: String? = null,
    onClick: () -> Unit,
    paddingValues: PaddingValues? = null,
) {
    val paddings = if (!needPadding)
        PaddingValues(BentoDSTheme.dims.x0)
    else
        paddingValues ?: PaddingValues(
            horizontal = mapHorizontalPadding(buttonSize = buttonSize),
            vertical = mapVerticalPadding(buttonSize = buttonSize)
        )

    val buttonPalette = mapButtonPaletteToButtonColors(buttonType = buttonType)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val contentColor = when {
        isPressed -> buttonPalette.hoverFg
        else -> iconsTint ?: buttonPalette.enabledFg
    }
    val backgroundColor = if (isPressed) buttonPalette.hoverBg else buttonPalette.enabledBg

    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides Dp.Unspecified,
    ) {
        Button(
            modifier = (if (fillMaxWidth) modifier.fillMaxWidth() else modifier)
                .defaultMinSize(1.dp, 1.dp),
            enabled = enabled,
            onClick = onClick,
            contentPadding = paddings,
            border = buttonBorder(buttonType, buttonPalette, enabled),
            colors = ButtonDefaults.buttonColors(
                contentColor = contentColor,
                containerColor = backgroundColor,
                disabledContentColor = buttonPalette.disabledFg,
                disabledContainerColor = buttonPalette.disabledBg,
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp, 0.dp),
            shape = BentoDSTheme.shapes.buttonShape,
            interactionSource = interactionSource,
        ) {
            leadingIcon?.let {
                BentoDSIcon(
                    iconSize = buttonIconSize,
                    iconRes = leadingIcon,
                    contentDescription = contentDescription.takeIf { text == null },
                )
            }
            if (leadingIcon != null && text != null)
                HSpace(w = BentoDSTheme.dims.x3)

            text?.let {
                Text(
                    text = text,
                    style = BentoDSTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (trailingIcon != null && text != null)
                HSpace(w = BentoDSTheme.dims.x3)
            trailingIcon?.let {
                BentoDSIcon(
                    iconSize = buttonIconSize,
                    iconRes = trailingIcon,
                    contentDescription = contentDescription.takeIf { text == null && leadingIcon == null },
                )
            }
        }
    }
}

private fun buttonBorder(buttonType: ButtonType, palette: PAButtonPalette, enabled: Boolean): BorderStroke? =
    when (buttonType) {
        ButtonType.PRIMARY_OUTLINED, ButtonType.SECONDARY_OUTLINED, ButtonType.NEGATIVE_OUTLINED,
        ButtonType.POSITIVE_OUTLINED, ButtonType.WARNING_OUTLINED -> BorderStroke(
            width = OUTLINED_BUTTON_BORDER_WIDTH,
            color = if (enabled) palette.enabledFg else palette.disabledFg,
        )

        else -> null
    }

/** Colors of the [buttonType] from the current [BentoDSTheme]. */
@Composable
fun mapButtonPaletteToButtonColors(buttonType: ButtonType) = when (buttonType) {
    ButtonType.PRIMARY_SOLID -> BentoDSTheme.buttonsColors.primarySolidButton
    ButtonType.PRIMARY_OUTLINED -> BentoDSTheme.buttonsColors.primaryOutlinedButton
    ButtonType.PRIMARY_TRANSPARENT -> BentoDSTheme.buttonsColors.primaryTransparentButton
    ButtonType.SECONDARY_SOLID -> BentoDSTheme.buttonsColors.secondarySolidButton
    ButtonType.SECONDARY_OUTLINED -> BentoDSTheme.buttonsColors.secondaryOutlinedButton
    ButtonType.SECONDARY_TRANSPARENT -> BentoDSTheme.buttonsColors.secondaryTransparentButton
    ButtonType.NEGATIVE_SOLID -> BentoDSTheme.buttonsColors.dangerSolidButton
    ButtonType.NEGATIVE_OUTLINED -> BentoDSTheme.buttonsColors.dangerOutlinedButton
    ButtonType.NEGATIVE_TRANSPARENT -> BentoDSTheme.buttonsColors.dangerTransparentButton
    ButtonType.POSITIVE_SOLID -> BentoDSTheme.buttonsColors.positiveSolidButton
    ButtonType.POSITIVE_OUTLINED -> BentoDSTheme.buttonsColors.positiveOutlinedButton
    ButtonType.POSITIVE_TRANSPARENT -> BentoDSTheme.buttonsColors.positiveTransparentButton
    ButtonType.WARNING_SOLID -> BentoDSTheme.buttonsColors.warningSolidButton
    ButtonType.WARNING_OUTLINED -> BentoDSTheme.buttonsColors.warningOutlinedButton
    ButtonType.WARNING_TRANSPARENT -> BentoDSTheme.buttonsColors.warningTransparentButton
}

@Composable
private fun mapVerticalPadding(buttonSize: ButtonSize) = when (buttonSize) {
    ButtonSize.S -> BentoDSTheme.dims.x2
    ButtonSize.M -> BentoDSTheme.dims.x3
    ButtonSize.L -> BentoDSTheme.dims.x4
}

@Composable
private fun mapHorizontalPadding(buttonSize: ButtonSize) = when (buttonSize) {
    ButtonSize.S, ButtonSize.M -> BentoDSTheme.dims.x4
    ButtonSize.L -> BentoDSTheme.dims.x6
}

@Composable
@Preview(
    showBackground = true,
    widthDp = 1000,
    heightDp = 1100,
)
private fun ButtonsPreview() {
    BentoDSTheme {
        Column {
            ButtonType.entries.forEach { type ->
                Row {
                    ButtonSize.entries.forEach { size ->
                        BentoDSButton(
                            buttonType = type,
                            buttonSize = size,
                            text = "Button",
                            leadingIcon = R.drawable.ic_placeholder,
                            trailingIcon = R.drawable.ic_placeholder,
                            onClick = {},
                        )
                        HSpace(w = 8.dp)
                    }
                }
                VSpace(h = 8.dp)
            }
            BentoDSButton(
                enabled = false,
                text = "Button",
                leadingIcon = R.drawable.ic_placeholder,
                trailingIcon = R.drawable.ic_placeholder,
                onClick = {},
                fillMaxWidth = true,
            )
            BentoDSButton(
                buttonType = ButtonType.NEGATIVE_OUTLINED,
                enabled = false,
                text = "Button",
                leadingIcon = R.drawable.ic_placeholder,
                trailingIcon = R.drawable.ic_placeholder,
                onClick = {},
                fillMaxWidth = true,
            )
        }
    }
}
