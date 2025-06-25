package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.utils.rememberNoRippleInteractionSource

@Composable
fun ModalContainer(
    modifier: Modifier = Modifier,
    showModal: Boolean,
    title: String,
    actionButton: ModalButton?,
    cancelButton: ModalButton?,
    onCloseModal: () -> Unit,
    modalContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        content.invoke()
        if (showModal) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BentoDSTheme.colors.bg.overlay)
                    .clickable(
                        interactionSource = rememberNoRippleInteractionSource(),
                        indication = null,
                        onClick = onCloseModal,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BentoDSModal(
                    title = title,
                    actionButton = actionButton,
                    cancelButton = cancelButton,
                    content = modalContent,
                    onCloseModal = onCloseModal
                )
            }
        }
    }
}