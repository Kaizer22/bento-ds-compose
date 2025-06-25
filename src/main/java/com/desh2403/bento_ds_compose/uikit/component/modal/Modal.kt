package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSCard
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.utils.rememberNoRippleInteractionSource


data class ModalButton(
    val text: String,
    val onClick: () -> Unit,
)

// TODO modal types
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
    content: @Composable () -> Unit,
) {
    BentoDSCard(
        modifier = modifier,
        paddingValues = paddingValues,
        color = bgColor,
        interactionSource = rememberNoRippleInteractionSource()
    ) {
        Column  {
            ModalTopBar(
                title = title,
                onCloseModal = onCloseModal,
                icon = topBarIcon,
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
    icon: Int?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
            )
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
        )
    }
}

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
                buttonType = ButtonType.SECONDARY_TRANSPARENT
            )
        }
        actionButton?.let {
            BentoDSButton(
                onClick = actionButton.onClick,
                text = actionButton.text,
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