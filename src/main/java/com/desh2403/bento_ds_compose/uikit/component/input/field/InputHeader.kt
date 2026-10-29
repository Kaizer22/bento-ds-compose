package com.desh2403.bento_ds_compose.uikit.component.input.field

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import kotlinx.coroutines.launch

/**
 * Header of an input field: the label and an optional info button that shows a hint in a tooltip.
 *
 * Bento DS: the `label` and `hint` of the form fields, e.g. [TextField](https://developer.bento-ds.com/Components/TextField).
 *
 * @param label label of the field. Bento DS `label`.
 * @param showInfoButton shows the info button.
 * @param infoIconTint tint of the info button.
 * @param infoMessage text of the tooltip shown when the info button is clicked. Bento DS `hint`.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputHeader(
    label: String?,
    showInfoButton: Boolean,
    infoIconTint: Color,
    infoMessage: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        label?.let {
            InputLabel(label)
        }
        if (showInfoButton) {
            FSpace()
            val tooltipState = rememberTooltipState()
            val scope = rememberCoroutineScope()
            TooltipBox(
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                tooltip = {
                    if (infoMessage != null) PlainTooltip { Text(text = infoMessage) }
                },
                state = tooltipState,
            ) {
                BentoDSIconButton(
                    iconRes = R.drawable.ic_question_circle,
                    size = IconSize.M,
                    needPadding = false,
                    iconTint = infoIconTint,
                    buttonType = ButtonType.PRIMARY_TRANSPARENT,
                    contentDescription = infoMessage,
                    onClick = { scope.launch { tooltipState.show() } },
                )
            }
        }
    }
    if (label != null || showInfoButton) {
        VSpace(h = BentoDSTheme.dims.x1 + BentoDSTheme.dims.x0_5)
    }
}

/** Label of an input field. Bento DS `label`. */
@Composable
fun InputLabel(
    label: String,
) {
    Text(
        text = label,
        style = BentoDSTheme.typography.labelMedium,
        color = BentoDSTheme.colors.text.primary,
    )
}
