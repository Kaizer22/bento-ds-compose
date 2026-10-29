package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.input.field.ERROR_STATES
import com.desh2403.bento_ds_compose.uikit.component.input.field.FieldState
import com.desh2403.bento_ds_compose.uikit.component.input.field.InputAssistiveText
import com.desh2403.bento_ds_compose.uikit.component.input.field.InputHeader
import com.desh2403.bento_ds_compose.uikit.component.loading.BentoDSInlineLoader
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Dashed drop area with a prompt and a "browse" button, or an inline loader while a file is uploading.
 * File picking and validation are up to the caller.
 *
 * Bento DS: [FileUploaderField](https://developer.bento-ds.com/Components/FileUploaderField)
 *
 * Differences from Bento DS: the component does not pick, validate or list files; it only shows
 * the area and reports the click on the button.
 *
 * @param modifier modifier of the field.
 * @param browseFileText prompt inside the area. Bento DS `texts`.
 * @param browseFileButtonText label of the button. Bento DS `texts`.
 * @param loadingText text next to the loader. Bento DS `texts`.
 * @param onBrowseFilesClick called when the button is clicked; open a file picker here.
 * @param label label above the field. Bento DS `label`.
 * @param assistiveText text under the field; an error message in the `_ERROR` states. Bento DS `issues`.
 * @param showInfoButton shows the info button next to the label.
 * @param state enabled, disabled, read-only and error states; only the enabled states enable the button.
 * Bento DS `disabled` and `issues`.
 * @param isLoading shows the loader instead of the prompt. Bento DS `isUploading`.
 */
@Composable
fun FileUploader(
    modifier: Modifier = Modifier,
    browseFileText: String,
    browseFileButtonText: String,
    loadingText: String,
    onBrowseFilesClick: () -> Unit,
    label: String? = null,
    assistiveText: String? = null,
    showInfoButton: Boolean = false,
    state: FieldState = FieldState.ENABLED,
    isLoading: Boolean,
) {
    val isError = state in ERROR_STATES
    val isEnabled = state == FieldState.ENABLED || state == FieldState.ENABLED_ERROR
    Column(modifier = modifier) {
        InputHeader(
            label = label,
            showInfoButton = showInfoButton,
            infoIconTint = BentoDSTheme.colors.fg.secondary,
        )
        VSpace(h = BentoDSTheme.dims.x1)
        val borderColor = when {
            !isEnabled -> BentoDSTheme.colors.outline.inputDisabled
            isError -> BentoDSTheme.colors.outline.negative
            else -> BentoDSTheme.colors.outline.inputEnabled
        }
        val localDensity = LocalDensity.current
        val stroke = remember(localDensity) {
            Stroke(
                width = localDensity.run { 1.dp.toPx() },
                pathEffect = PathEffect
                    .dashPathEffect(
                        floatArrayOf(10f, 10f),
                        0f
                    )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRoundRect(
                        color = borderColor,
                        style = stroke,
                        cornerRadius = CornerRadius(
                            x = 16.dp.toPx(),
                            y = 16.dp.toPx(),
                        )
                    )
                }
                .padding(BentoDSTheme.dims.x4),
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                BentoDSInlineLoader(
                    text = loadingText,
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = browseFileText,
                        style = BentoDSTheme.typography.bodyMedium,
                        color = BentoDSTheme.colors.text.primary,
                    )
                    VSpace(h = BentoDSTheme.dims.x2)
                    BentoDSButton(
                        text = browseFileButtonText,
                        buttonType = ButtonType.SECONDARY_OUTLINED,
                        buttonSize = ButtonSize.S,
                        enabled = isEnabled,
                        onClick = onBrowseFilesClick,
                    )
                }
            }
        }
        VSpace(h = BentoDSTheme.dims.x1)
        InputAssistiveText(
            assistiveText = assistiveText,
            errorIconTint = BentoDSTheme.colors.text.negative,
            isError = isError,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FileUploaderPreview() {
    BentoDSTheme {
        FileUploader(
            browseFileButtonText = "Browse files",
            label = "Label",
            showInfoButton = true,
            assistiveText = "Assistive text",
            onBrowseFilesClick = {},
            isLoading = false,
            loadingText = "Loading...",
            browseFileText = "Select file",
        )
    }
}