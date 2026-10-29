package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme
import com.desh2403.bento_ds_compose.uikit.utils.rememberNoRippleInteractionSource

/**
 * Shows [content] and, while [showModal], a [BentoDSModal] centered over it on a scrim.
 * Clicking the scrim or pressing the system back button closes the modal.
 *
 * Bento DS: [Modal](https://developer.bento-ds.com/Components/Modal)
 *
 * @param modifier modifier of the container.
 * @param showModal shows the modal.
 * @param title title of the modal.
 * @param actionButton primary button of the modal.
 * @param cancelButton secondary button of the modal.
 * @param onCloseModal called when the modal should close: close button, scrim click or back press.
 * @param modalContent content of the modal.
 * @param content content under the modal.
 */
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
            BackHandler(onBack = onCloseModal)
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
                    modifier = Modifier.pointerInput(Unit) { detectTapGestures { } },
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