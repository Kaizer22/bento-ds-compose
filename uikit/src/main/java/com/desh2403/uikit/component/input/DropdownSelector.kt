package com.desh2403.uikit.component.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownSelector(
    items: List<String>,
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit,
) {
    if (selectedItem <= items.size) {
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
            Text(text = items[selectedItem], modifier = Modifier.clickable { expanded = true })
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                items.forEachIndexed { index, item ->
//                    DropdownMenuItem(onClick = {
//                        expanded = false
//                        onItemSelected(index)
//                    }) {
//                        Text(item)
//                    }
                }
            }
        }
    }
}