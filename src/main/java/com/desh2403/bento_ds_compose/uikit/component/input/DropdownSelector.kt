package com.desh2403.bento_ds_compose.uikit.component.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Text of the selected item that opens a dropdown menu with all items when clicked.
 *
 * Bento DS: [SelectField](https://developer.bento-ds.com/Components/SelectField) describes a full select field;
 * this is a compact variant without a label or a border, used by [TimePicker]. The component is an
 * extension of this library; for a form field use
 * [com.desh2403.bento_ds_compose.uikit.component.input.field.BentoDSSelectField].
 *
 * @param items labels of the items.
 * @param selectedItem index of the selected item; nothing is shown if it is out of [items].
 * @param onItemSelected called with the index of the clicked item.
 * @param modifier modifier of the selector.
 */
@Composable
fun DropdownSelector(
    items: List<String>,
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selectedItem !in items.indices) return
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.padding(BentoDSTheme.dims.x5),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.clickable(role = Role.DropdownList) { expanded = true },
            text = items[selectedItem],
            style = BentoDSTheme.typography.bodyLarge,
            color = BentoDSTheme.colors.text.primary,
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(text = item, style = BentoDSTheme.typography.bodyMedium) },
                    onClick = {
                        expanded = false
                        onItemSelected(index)
                    },
                )
            }
        }
    }
}
