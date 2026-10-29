package com.desh2403.bento_ds_compose.uikit.component.input.field

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Read-only field that opens a dropdown menu with the options and shows the selected one.
 *
 * Bento DS: [SelectField](https://developer.bento-ds.com/Components/SelectField)
 *
 * Differences from Bento DS: single selection only, without search, clearing, a placeholder or an
 * assistive text.
 *
 * @param modifier modifier of the field.
 * @param selectedVariant label of the selected option. Bento DS `value`.
 * @param variants labels of the options. Bento DS `options`.
 * @param label label above the field. Bento DS `label`.
 * @param onSelectVariant called with the clicked option. Bento DS `onChange`.
 * @param state enabled, disabled, read-only and error states; only the enabled states open the menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BentoDSSelectField(
    modifier: Modifier = Modifier,
    selectedVariant: String,
    variants: List<String>,
    label: String? = null,
    onSelectVariant: (String) -> Unit,
    state: FieldState = FieldState.ENABLED,
) {
    var expanded by remember { mutableStateOf(false) }
    val canExpand = state == FieldState.ENABLED || state == FieldState.ENABLED_ERROR
    ExposedDropdownMenuBox(
        expanded = expanded && canExpand,
        onExpandedChange = { expanded = canExpand && !expanded }
    ) {
        BentoDSTextField(
            modifier = modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            value = selectedVariant,
            readOnlyInputField = true,
            state = state,
            label = label,
            onValueChange = {},
            trailingIcon = InputFieldIcon(
                R.drawable.ic_chevron_down,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded && canExpand,
            onDismissRequest = { expanded = false },
            containerColor = BentoDSTheme.colors.bg.primary,
            shape = BentoDSTheme.shapes.menuShape,
        ) {
            variants.forEach { variant ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = variant,
                            style = BentoDSTheme.typography.bodyMedium,
                        )
                    },
                    onClick = {
                        onSelectVariant.invoke(variant)
                        expanded = false
                    },
                )
            }
        }
    }
}