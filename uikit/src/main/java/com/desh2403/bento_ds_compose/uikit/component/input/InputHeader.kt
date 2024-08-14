package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
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

@Composable
fun InputHeader(
    label: String?,
    showInfoButton: Boolean,
    infoIconTint: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        label?.let {
            Text(
                text = label,
                style = BentoDSTheme.typography.labelSmall,
                color = BentoDSTheme.colors.text.secondary,
            )
        }
        if (showInfoButton) {
            //val infoTooltipState = rememberTooltipState()
            val scope = rememberCoroutineScope()
            FSpace()
            //TooltipBox(
            //tooltip = { Text(text = infoMessage.orEmpty()) },
            //state = infoTooltipState,
            //positionProvider = TooltipDefaults
            //.rememberPlainTooltipPositionProvider(),
            //) {
            BentoDSIconButton(
                iconRes = R.drawable.ic_question_circle,
                size = IconSize.M,
                isNeedPadding = false,
                iconTint = infoIconTint,
                buttonType = ButtonType.PRIMARY_TRANSPARENT,
                // TODO
                onClick = {
                    scope.launch {
                        //infoTooltipState.show()
                    }
                },
            )
            //}
        }
        if (label != null || showInfoButton) {
            VSpace(h = BentoDSTheme.dimensions.x1)
        }
    }
}