package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Status of a [Toast]: Bento DS `kind`. */
enum class BentoDSToastType {
    INFORMATIVE, POSITIVE, WARNING, NEGATIVE, SECONDARY
}

/**
 * Action of a [Toast].
 *
 * @param name label of the action.
 * @param onClick called when the action is clicked.
 */
data class Action(
    val name: String,
    val onClick: () -> Unit,
)

/**
 * Short status message with an optional action and a close button. The surface keeps an outer margin
 * so it does not touch the screen edges. Showing, queueing and hiding toasts are up to the caller.
 *
 * Bento DS: [Toast](https://developer.bento-ds.com/Components/Toast)
 *
 * @param modifier modifier of the toast.
 * @param type status of the toast. Bento DS `kind`.
 * @param message text of the toast. Bento DS `message`.
 * @param action action button. Bento DS `action`.
 * @param onClose called when the close button is clicked. Bento DS `onDismiss`.
 * @param closeButtonLabel accessibility label of the close button.
 */
@Composable
fun Toast(
    modifier: Modifier = Modifier,
    type: BentoDSToastType = BentoDSToastType.INFORMATIVE,
    message: String,
    action: Action? = null,
    onClose: () -> Unit,
    closeButtonLabel: String? = null,
) {
    val messageColor = when (type) {
        BentoDSToastType.INFORMATIVE -> BentoDSTheme.colors.text.informative
        BentoDSToastType.POSITIVE -> BentoDSTheme.colors.text.positive
        BentoDSToastType.WARNING -> BentoDSTheme.colors.text.warning
        BentoDSToastType.NEGATIVE -> BentoDSTheme.colors.text.negative
        BentoDSToastType.SECONDARY -> BentoDSTheme.colors.text.secondary
    }
    val backgroundColor = when (type) {
        BentoDSToastType.INFORMATIVE -> BentoDSTheme.colors.bg.informative
        BentoDSToastType.POSITIVE -> BentoDSTheme.colors.bg.positive
        BentoDSToastType.WARNING -> BentoDSTheme.colors.bg.warning
        BentoDSToastType.NEGATIVE -> BentoDSTheme.colors.bg.negative
        BentoDSToastType.SECONDARY -> BentoDSTheme.colors.bg.secondary
    }
    BentoDSCard(
        modifier = modifier.padding(BentoDSTheme.dims.x3),
        color = backgroundColor,
        paddingValues = PaddingValues(
            horizontal = BentoDSTheme.dims.x6,
            vertical = BentoDSTheme.dims.x3,
        ),
    ) {
        Row(
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
                    HSpace(w = BentoDSTheme.dims.x4)
                    BentoDSButton(
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        text = action.name,
                        onClick = action.onClick,
                    )
                }
                HSpace(w = BentoDSTheme.dims.x4)
                BentoDSIconButton(
                    buttonType = ButtonType.SECONDARY_TRANSPARENT,
                    needPadding = false,
                    iconRes = R.drawable.ic_x,
                    size = IconSize.L,
                    contentDescription = closeButtonLabel,
                    onClick = onClose,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ToastPreview() {
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