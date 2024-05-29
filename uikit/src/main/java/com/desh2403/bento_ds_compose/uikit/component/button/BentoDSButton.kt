package com.desh2403.bento_ds_compose.uikit.component.button

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme


enum class ButtonType {
    PRIMARY_SOLID, PRIMARY_OUTLINED, PRIMARY_TRANSPARENT,
    SECONDARY_SOLID, SECONDARY_OUTLINED, SECONDARY_TRANSPARENT,
    DANGER_SOLID, DANGER_OUTLINED, DANGER_TRANSPARENT,
    // POSITIVE, WARNING
}

enum class ButtonSize {
    S, M, L,
    ICON_S, ICON_M, ICON_L, ICON_XL
}

enum class ButtonIconSize {
    S, M, L, XL
}

private const val OUTLINED_BUTTON_BORDER_WIDTH = 1
//private val defaultStroke = BorderStroke(
//    width = OUTLINED_BUTTON_BORDER_WIDTH.dp,
//    color = Color.Black,
//)
//
//val fillMaxWidthModifier = Modifier.fillMaxWidth()

// 8,12,16,24
//@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BentoDSButton(
    modifier: Modifier = Modifier,
    isGotPadding: Boolean = true,
    isFillMaxWidth: Boolean = false,
    buttonType: ButtonType = ButtonType.PRIMARY_SOLID,
    buttonSize: ButtonSize = ButtonSize.M,
    buttonIconSize: ButtonIconSize = ButtonIconSize.XL,
    isEnabled: Boolean = true,
    text: String? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    iconsTint: Color? = null,
    onClick: () -> Unit,
) {
    val horizontalPadding = if (isGotPadding) mapHorizontalPadding(buttonSize = buttonSize)
    else BentoDSTheme.dimensions.x0
    val verticalPadding = if (isGotPadding) mapVerticalPadding(buttonSize = buttonSize)
    else BentoDSTheme.dimensions.x0
    val iconSize = mapButtonIconSize(buttonIconSize = buttonIconSize)

    val buttonPalette = mapButtonPaletteToButtonColors(buttonType = buttonType)

    val elevation = ButtonDefaults.elevatedButtonElevation(0.dp, 0.dp)

    val borderStroke = when (buttonType) {
        ButtonType.PRIMARY_OUTLINED, ButtonType.SECONDARY_OUTLINED, ButtonType.DANGER_OUTLINED ->
            BorderStroke(
                width = OUTLINED_BUTTON_BORDER_WIDTH.dp,
                color = buttonPalette.enabledFg,
            )

        else -> null
    }

    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false,
    ) {
        val isFillModifier = if (isFillMaxWidth) modifier.fillMaxWidth() else modifier

        var contentColor by remember {
            mutableStateOf(
                // Костыль для установки tint в icon button
                iconsTint ?: buttonPalette.enabledFg
            )
        }
        var backgroundColor by remember { mutableStateOf(buttonPalette.enabledBg) }

        Button(
            modifier = isFillModifier
                .defaultMinSize(1.dp, 1.dp)
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            contentColor = buttonPalette.hoverFg
                            backgroundColor = buttonPalette.hoverBg
                        }

                        MotionEvent.ACTION_UP -> {
                            contentColor = buttonPalette.enabledFg
                            backgroundColor = buttonPalette.enabledBg
                            onClick.invoke()
                        }

                        MotionEvent.ACTION_BUTTON_RELEASE,
                        MotionEvent.ACTION_HOVER_EXIT,
                        MotionEvent.ACTION_CANCEL -> {
                            contentColor = buttonPalette.enabledFg
                            backgroundColor = buttonPalette.enabledBg
                        }
                    }
                    true
                },
            enabled = isEnabled,
            onClick = onClick,
            contentPadding = PaddingValues(
                horizontal = horizontalPadding,
                vertical = verticalPadding,
            ),
            border = borderStroke,
            colors = ButtonDefaults.buttonColors(
                contentColor = contentColor,
                containerColor = backgroundColor,
                disabledContentColor = buttonPalette.disabledFg,
                disabledContainerColor = buttonPalette.disabledBg,
            ),
            elevation = elevation,
            shape = BentoDSTheme.shapes.buttonShape,
        ) {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "",
                )
            }
            if (leadingIcon != null && text != null)
                HSpace(w = BentoDSTheme.dimensions.x3)

            text?.let {
                Text(
                    text = text,
                    style = BentoDSTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (trailingIcon != null && text != null)
                HSpace(w = BentoDSTheme.dimensions.x3)
            trailingIcon?.let {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
fun mapButtonPaletteToButtonColors(buttonType: ButtonType) = when (buttonType) {
    ButtonType.PRIMARY_SOLID -> BentoDSTheme.buttonsColors.primarySolidButton
    ButtonType.PRIMARY_OUTLINED -> BentoDSTheme.buttonsColors.primaryOutlinedButton
    ButtonType.PRIMARY_TRANSPARENT -> BentoDSTheme.buttonsColors.primaryTransparentButton
    ButtonType.SECONDARY_SOLID -> BentoDSTheme.buttonsColors.secondarySolidButton
    ButtonType.SECONDARY_OUTLINED -> BentoDSTheme.buttonsColors.secondaryOutlinedButton
    ButtonType.SECONDARY_TRANSPARENT -> BentoDSTheme.buttonsColors.secondaryTransparentButton
    ButtonType.DANGER_SOLID -> BentoDSTheme.buttonsColors.dangerSolidButton
    ButtonType.DANGER_OUTLINED -> BentoDSTheme.buttonsColors.dangerOutlinedButton
    ButtonType.DANGER_TRANSPARENT -> BentoDSTheme.buttonsColors.dangerTransparentButton
}

@Composable
private fun mapButtonIconSize(buttonIconSize: ButtonIconSize) = when (buttonIconSize) {
    ButtonIconSize.S -> BentoDSTheme.dimensions.x2
    ButtonIconSize.M -> BentoDSTheme.dimensions.x3
    ButtonIconSize.L -> BentoDSTheme.dimensions.x4
    ButtonIconSize.XL -> BentoDSTheme.dimensions.x6
}

@Composable
private fun mapVerticalPadding(buttonSize: ButtonSize) = when (buttonSize) {
    ButtonSize.S -> BentoDSTheme.dimensions.x2
    ButtonSize.M -> BentoDSTheme.dimensions.x3
    ButtonSize.L -> BentoDSTheme.dimensions.x4
    ButtonSize.ICON_S, ButtonSize.ICON_M -> BentoDSTheme.dimensions.x2
    ButtonSize.ICON_L, ButtonSize.ICON_XL -> BentoDSTheme.dimensions.x4
}

@Composable
private fun mapHorizontalPadding(buttonSize: ButtonSize) = when (buttonSize) {
    ButtonSize.S, ButtonSize.M -> BentoDSTheme.dimensions.x4
    ButtonSize.L -> BentoDSTheme.dimensions.x6
    ButtonSize.ICON_S, ButtonSize.ICON_M -> BentoDSTheme.dimensions.x2
    ButtonSize.ICON_L, ButtonSize.ICON_XL -> BentoDSTheme.dimensions.x4
}


