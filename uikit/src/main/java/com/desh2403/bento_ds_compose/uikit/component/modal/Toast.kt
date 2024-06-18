package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonIconSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

enum class BentoDSToastType {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, SECONDARY
}

data class Action(
    val name: String,
    val onClick: () -> Unit,
)

@Composable
fun Toast(
    modifier: Modifier = Modifier,
    type: BentoDSToastType = BentoDSToastType.INFORMATIVE,
    message: String,
    action: Action? = null,
    onClose: () -> Unit,
) {
    val messageColor = when (type) {
        BentoDSToastType.INFORMATIVE -> BentoDSTheme.colors.textInformative
        BentoDSToastType.POSITIVE -> BentoDSTheme.colors.textPositive
        BentoDSToastType.WARNING -> BentoDSTheme.colors.textWarning
        BentoDSToastType.NEGATIVE -> BentoDSTheme.colors.textNegative
        BentoDSToastType.SECONDARY -> BentoDSTheme.colors.textSecondary
    }
    val backgroundColor = when (type) {
        BentoDSToastType.INFORMATIVE -> BentoDSTheme.colors.bgInformative
        BentoDSToastType.POSITIVE -> BentoDSTheme.colors.bgPositive
        BentoDSToastType.WARNING -> BentoDSTheme.colors.bgWarning
        BentoDSToastType.NEGATIVE -> BentoDSTheme.colors.bgNegative
        BentoDSToastType.SECONDARY -> BentoDSTheme.colors.bgSecondary
    }
    BentoDSCard(
        color = backgroundColor,
        paddingValues = PaddingValues(
            horizontal = BentoDSTheme.dimensions.x6,
            vertical = BentoDSTheme.dimensions.x3,
        ),
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = message,
                style = BentoDSTheme.typography.bodyMedium,
                color = messageColor,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                action?.let {
                    HSpace(w = BentoDSTheme.dimensions.x4)
                    BentoDSButton(
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        text = action.name,
                        onClick = action.onClick,
                    )
                }
                HSpace(w = BentoDSTheme.dimensions.x4)
                BentoDSIconButton(
                    buttonType = ButtonType.SECONDARY_TRANSPARENT,
                    isNeedPadding = false,
                    iconRes = R.drawable.ic_x,
                    size = ButtonIconSize.L,
                    onClick = onClose,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToastPreview() {
    BentoDSTheme {
        Column {
            Toast(
                modifier = Modifier.fillMaxWidth(),
                type = BentoDSToastType.INFORMATIVE,
                message = "Message",
                action = Action(
                    name = "Action",
                    onClick = {},
                ),
                onClose = {},
            )
            Toast(
                modifier = Modifier.fillMaxWidth(),
                type = BentoDSToastType.NEGATIVE,
                message = "Message",
                action = Action(
                    name = "Action",
                    onClick = {},
                ),
                onClose = {},
            )
            Toast(
                modifier = Modifier.fillMaxWidth(),
                type = BentoDSToastType.POSITIVE,
                message = "Message",
                action = Action(
                    name = "Action",
                    onClick = {},
                ),
                onClose = {},
            )
            Toast(
                modifier = Modifier.fillMaxWidth(),
                type = BentoDSToastType.WARNING,
                message = "Message",
                action = Action(
                    name = "Action",
                    onClick = {},
                ),
                onClose = {},
            )
            Toast(
                modifier = Modifier.fillMaxWidth(),
                type = BentoDSToastType.SECONDARY,
                message = "Message",
                action = Action(
                    name = "Action",
                    onClick = {},
                ),
                onClose = {},
            )

        }
    }
}