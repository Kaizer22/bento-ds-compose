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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BentoDSSelectField(
    modifier: Modifier = Modifier,
    selectedVariant: Int,
    variants: List<String>,
    label: String? = null,
    onSelectVariant: (Int) -> Unit,
    state: FieldState = FieldState.ENABLED,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        BentoDSTextField(
            modifier = modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable),
            value = variants[selectedVariant],
            readOnlyInputField = true,
            state = state,
            label = label,
            onValueChange = {},
            trailingIcon = InputFieldIcon(
                R.drawable.ic_chevron_down,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = BentoDSTheme.colors.bg.primary,
            shape = BentoDSTheme.shapes.menuShape,
        ) {
            variants.forEachIndexed { index, variant ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = variant,
                            style = BentoDSTheme.typography.bodyMedium,
                        )
                    },
                    onClick = {
                        onSelectVariant.invoke(index)
                        // only for single selection
                        expanded = false
                    },
                )
            }
        }
    }
}