package com.desh2403.bento_ds_compose.uikit.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Modal bottom sheet with a scrim, a drag handle, a title and an element next to the title.
 *
 * Bento DS has no bottom sheet; the closest one is [Modal](https://developer.bento-ds.com/Components/Modal).
 * The whole component is an extension of this library.
 *
 * @param onDismissRequest called when the sheet is swiped down or the scrim is clicked.
 * @param modifier modifier of the sheet.
 * @param title title of the sheet.
 * @param titleAction element at the end of the title row, e.g. a switch.
 * @param skipPartiallyExpanded opens the sheet fully at once, e.g. for long scrollable content.
 * @param content content of the sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BentoDSBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleAction: (@Composable () -> Unit)? = null,
    skipPartiallyExpanded: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = skipPartiallyExpanded,
        ),
        shape = BentoDSTheme.shapes.bottomSheetModalShape,
        containerColor = BentoDSTheme.colors.bg.elevated,
        contentColor = BentoDSTheme.colors.text.primary,
        scrimColor = BentoDSTheme.colors.bg.scrim,
        tonalElevation = BentoDSTheme.dims.x0,
        dragHandle = { BentoDSBottomSheetHandle() },
    ) {
        Column(
            modifier = Modifier.padding(
                start = BentoDSTheme.dims.x4,
                end = BentoDSTheme.dims.x4,
                bottom = BentoDSTheme.dims.x4,
            ),
        ) {
            if (title != null || titleAction != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = BentoDSTheme.dims.x4),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = title.orEmpty(),
                        style = BentoDSTheme.typography.titleLarge,
                        color = BentoDSTheme.colors.text.primary,
                    )
                    titleAction?.let {
                        HSpace(w = BentoDSTheme.dims.x3)
                        it.invoke()
                    }
                }
            }
            content()
        }
    }
}

@Composable
private fun BentoDSBottomSheetHandle() {
    Box(
        modifier = Modifier
            .padding(top = BentoDSTheme.dims.x2, bottom = BentoDSTheme.dims.x3)
            .size(width = BentoDSTheme.dims.x8, height = BentoDSTheme.dims.x1)
            .background(
                color = BentoDSTheme.colors.outline.decorative,
                shape = BentoDSTheme.shapes.roundShape,
            ),
    )
}

@Preview
@Composable
private fun BentoDSBottomSheetHandlePreview() {
    BentoDSTheme {
        Box(
            modifier = Modifier.background(BentoDSTheme.colors.bg.elevated),
            contentAlignment = Alignment.Center,
        ) {
            BentoDSBottomSheetHandle()
        }
    }
}
