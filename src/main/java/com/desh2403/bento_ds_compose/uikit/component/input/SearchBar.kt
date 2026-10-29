package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSIconButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSIcon
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Search field with a magnifier icon and a clear button that is shown while the field is not empty.
 * The keyboard search action hides the keyboard.
 *
 * Bento DS: [SearchBar](https://developer.bento-ds.com/Components/SearchBar)
 *
 * Differences from Bento DS: no `disabled`, `autoFocus` or `onBlur`.
 *
 * @param value current text. Bento DS `value`.
 * @param onValueChange called with the new text. Bento DS `onChange`.
 * @param modifier modifier of the field.
 * @param placeholder text shown while the field is empty. Bento DS `placeholder`.
 * @param clearContentDescription accessibility label of the clear button. Bento DS `clearButtonLabel`.
 */
@Composable
fun BentoDSSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    clearContentDescription: String? = null,
) {
    val focusManager = LocalFocusManager.current
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = BentoDSTheme.typography.bodyLarge.copy(
            color = BentoDSTheme.colors.text.primary,
        ),
        cursorBrush = SolidColor(BentoDSTheme.colors.text.primary),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BentoDSTheme.dims.x12)
                    .background(
                        color = BentoDSTheme.colors.bg.secondary,
                        shape = BentoDSTheme.shapes.inputShape,
                    )
                    .padding(start = BentoDSTheme.dims.x4, end = BentoDSTheme.dims.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BentoDSIcon(
                    iconSize = IconSize.L,
                    iconTint = BentoDSTheme.colors.fg.secondary,
                    iconRes = R.drawable.ic_search,
                )
                HSpace(w = BentoDSTheme.dims.x2)
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder,
                            style = BentoDSTheme.typography.bodyLarge,
                            color = BentoDSTheme.colors.text.secondary,
                            maxLines = 1,
                        )
                    }
                    innerTextField()
                }
                if (value.isNotEmpty()) {
                    BentoDSIconButton(
                        contentDescription = clearContentDescription,
                        iconRes = R.drawable.ic_x,
                        size = IconSize.L,
                        buttonType = ButtonType.SECONDARY_TRANSPARENT,
                        onClick = { onValueChange("") },
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun BentoDSSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    BentoDSTheme {
        BentoDSSearchBar(
            value = query,
            onValueChange = { query = it },
            placeholder = "Search",
        )
    }
}
