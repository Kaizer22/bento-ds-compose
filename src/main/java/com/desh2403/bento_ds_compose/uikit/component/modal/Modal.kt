package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme


/**
 * Button of a [BentoDSModal].
 *
 * @param text label of the button.
 * @param onClick called when the button is clicked.
 * @param buttonType style of the button, e.g. [ButtonType.NEGATIVE_SOLID] for a deletion;
 * `null` uses the default style for the position of the button.
 */
data class ModalButton(
    val text: String,
    val onClick: () -> Unit,
    val buttonType: ButtonType? = null,
)

/**
 * Dialog surface with an optional icon, a title, a close button, content, and cancel and action buttons.
 * The surface keeps an outer margin so it does not touch the screen edges.
 *
 * Bento DS: [Modal](https://developer.bento-ds.com/Components/Modal)
 *
 * Differences from Bento DS:
 * - the modal is a surface only; show it over the screen with [ModalContainer] or a dialog;
 * - the button styles replace `kind` and `isDestructive`; there is no loading or error state;
 * - [topBarIcon], [bgColor] and [paddingValues] are extensions.
 *
 * @param modifier modifier of the modal.
 * @param title title of the modal. Bento DS `title`.
 * @param topBarIcon icon before the title.
 * @param actionButton primary button. Bento DS `primaryAction`.
 * @param cancelButton secondary button. Bento DS `secondaryAction`.
 * @param onCloseModal called when the close button is clicked. Bento DS `onClose`.
 * @param bgColor background of the modal.
 * @param paddingValues padding around the content.
 * @param closeButtonLabel accessibility label of the close button. Bento DS `closeButtonLabel`.
 * @param content content of the modal.
 */
@Composable
fun BentoDSModal(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes topBarIcon: Int? = null,
    actionButton: ModalButton? = null,
    cancelButton: ModalButton? = null,
    onCloseModal: () -> Unit,
    bgColor: Color = BentoDSTheme.colors.bg.primary,
    paddingValues: PaddingValues = PaddingValues(BentoDSTheme.dims.x6),
    closeButtonLabel: String? = null,
    content: @Composable () -> Unit,
) {
    BentoDSCard(
        modifier = modifier.padding(BentoDSTheme.dims.x3),
        paddingValues = paddingValues,
        color = bgColor,
    ) {
        Column {
            ModalTopBar(
                title = title,
                onCloseModal = onCloseModal,
                icon = topBarIcon,
                closeButtonLabel = closeButtonLabel,
            )
            VSpace(BentoDSTheme.dims.x6)
            content.invoke()
            VSpace(BentoDSTheme.dims.x6)
            ModalBottomBar(
                actionButton = actionButton,
                cancelButton = cancelButton,
            )
        }
    }
}

@Composable
private fun ModalTopBar(
    title: String,
    onCloseModal: () -> Unit,
    icon: Int?,
    closeButtonLabel: String?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
            )
            HSpace(w = BentoDSTheme.dims.x2)
        }
        Text(
            text = title,
            style = BentoDSTheme.typography.titleLarge,
            color = BentoDSTheme.colors.text.primary,
        )
        FSpace()
        BentoDSIconButton(
            onClick = onCloseModal,
            iconRes = R.drawable.ic_x,
            buttonType = ButtonType.SECONDARY_TRANSPARENT,
            size = IconSize.M,
            needPadding = false,
            iconTint = BentoDSTheme.colors.fg.primary,
            contentDescription = closeButtonLabel,
        )
    }
}

/** Cancel and action buttons of a [BentoDSModal], aligned to the end. */
@Composable
fun ModalBottomBar(
    actionButton: ModalButton?,
    cancelButton: ModalButton?,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BentoDSTheme.dims.x4)
    ) {
        FSpace()
        cancelButton?.let {
            BentoDSButton(
                onClick = cancelButton.onClick,
                text = cancelButton.text,
                buttonType = cancelButton.buttonType ?: ButtonType.SECONDARY_TRANSPARENT,
            )
        }
        actionButton?.let {
            BentoDSButton(
                onClick = actionButton.onClick,
                text = actionButton.text,
                buttonType = actionButton.buttonType ?: ButtonType.PRIMARY_SOLID,
            )
        }
    }
}

@Preview
@Composable
private fun ModalPreview() {
    BentoDSTheme {
        BentoDSModal(
            title = "Title",
            actionButton = ModalButton("Action", {}),
            cancelButton = ModalButton("Cancel", {}),
            onCloseModal = {},
        ) {
            Text(
                "Modal content"
            )
        }
    }
}

@Preview
@Composable
private fun DestructiveModalPreview() {
    BentoDSTheme {
        BentoDSModal(
            title = "Delete all data?",
            actionButton = ModalButton("Delete", {}, buttonType = ButtonType.NEGATIVE_SOLID),
            cancelButton = ModalButton("Cancel", {}),
            onCloseModal = {},
        ) {
            Text(
                text = "This can't be undone.",
                style = BentoDSTheme.typography.bodyMedium,
            )
        }
    }
}
