package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Padded column with an optional title, for the content of a Material `ModalBottomSheet`.
 * Prefer [BentoDSBottomSheet], which includes the sheet itself.
 *
 * @param title title above the content.
 * @param content content of the sheet.
 */
@Composable
fun ModalBottomSheetContainer(
    title: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                top = BentoDSTheme.dims.x10,
                start = BentoDSTheme.dims.x6,
                end = BentoDSTheme.dims.x6,
                bottom = BentoDSTheme.dims.x6,
            )
    ) {
        title?.let {
            Text(
                text = it,
                style = BentoDSTheme.typography.titleLarge,
                color = BentoDSTheme.colors.text.primary,
            )
            VSpace(h = BentoDSTheme.dims.x6)
        }
        content.invoke()
    }
}