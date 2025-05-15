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
import com.desh2403.bento_ds_compose.uikit.component.loading.InlineLoader
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

// TODO FileUploader
// - different states
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
    state: InputFieldState = InputFieldState.ENABLED,
    isLoading: Boolean,
) {
    val isError = remember(state) { ERROR_STATES.contains(state) }
    Column(modifier = modifier) {
        InputHeader(
            label = label,
            showInfoButton = showInfoButton,
            infoIconTint = BentoDSTheme.colors.text.negative,
        )
        VSpace(h = BentoDSTheme.dimensions.x1)
        val borderColor = BentoDSTheme.colors.outline.inputEnabled
        val localDensity = LocalDensity.current
        val stroke = remember {
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
                .padding(BentoDSTheme.dimensions.x4),
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                InlineLoader(
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
                    VSpace(h = BentoDSTheme.dimensions.x2)
                    BentoDSButton(
                        text = browseFileButtonText,
                        buttonType = ButtonType.SECONDARY_OUTLINED,
                        buttonSize = ButtonSize.S,
                        onClick = onBrowseFilesClick,
                    )
                }
            }
        }
        VSpace(h = BentoDSTheme.dimensions.x1)
        InputAssistiveText(
            assistiveText = assistiveText,
            errorIconTint = BentoDSTheme.colors.text.negative,
            isError = isError,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FileUploaderPreview() {
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