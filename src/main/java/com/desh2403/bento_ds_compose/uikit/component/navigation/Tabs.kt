package com.desh2403.bento_ds_compose.uikit.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.navigation.BentoDSTabType.FOLDER
import com.desh2403.bento_ds_compose.uikit.component.navigation.BentoDSTabType.UNDERLINE
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/** Look of [BentoDSTabs]: [FOLDER] tabs with a filled selected tab, or [UNDERLINE] tabs. */
enum class BentoDSTabType {
    FOLDER, UNDERLINE
}

/** Size of [BentoDSTabs]. */
enum class BentoDSTabSize {
    MEDIUM, LARGE
}

/**
 * Tab of [BentoDSTabs].
 *
 * @param title label of the tab.
 * @param iconRes icon before the label.
 * @param enabled whether the tab can be selected.
 * @param notificationMarker shows a dot after the label.
 */
data class BentoDSTab(
    val title: String,
    val iconRes: Int? = null,
    val enabled: Boolean = true,
    val notificationMarker: Boolean = false,
)

/**
 * Row of tabs above the content of the selected tab. The row scrolls horizontally when the tabs do not fit.
 *
 * Bento DS: [Tabs](https://developer.bento-ds.com/Components/Tabs)
 *
 * Extensions over Bento DS: the folder and underline looks, icons, disabled tabs and notification markers.
 *
 * @param modifier modifier of the tabs.
 * @param selectedDSTab selected tab.
 * @param size size of the tabs.
 * @param type look of the tabs.
 * @param tabs all tabs.
 * @param onSelectTab called with the clicked tab.
 * @param content content of the selected tab.
 */
@Composable
fun BentoDSTabs(
    modifier: Modifier = Modifier,
    selectedDSTab: BentoDSTab,
    size: BentoDSTabSize = BentoDSTabSize.MEDIUM,
    type: BentoDSTabType = BentoDSTabType.FOLDER,
    tabs: List<BentoDSTab>,
    onSelectTab: (BentoDSTab) -> Unit,
    content: @Composable (BentoDSTab) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        TabsRow(
            size = size,
            type = type,
            selectedDSTab = selectedDSTab,
            tabs = tabs,
            onSelectTab = onSelectTab
        )
        content.invoke(selectedDSTab)
    }
}

@Composable
private fun TabsRow(
    size: BentoDSTabSize,
    type: BentoDSTabType,
    selectedDSTab: BentoDSTab,
    tabs: List<BentoDSTab>,
    onSelectTab: (BentoDSTab) -> Unit
) {
    val outlineColor = BentoDSTheme.colors.outline.decorative
    val outlineWidth = BentoDSTheme.dims.x0_25
    Row(
        modifier = Modifier
            .then(
                if (type == BentoDSTabType.UNDERLINE) Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val y = this.size.height - outlineWidth.toPx() / 2
                        drawLine(
                            color = outlineColor,
                            start = Offset(0f, y),
                            end = Offset(this.size.width, y),
                            strokeWidth = outlineWidth.toPx(),
                        )
                    }
                else Modifier
            )
            .horizontalScroll(
                rememberScrollState()
            )
    ) {
        tabs.forEach { tab ->
            when (type) {
                BentoDSTabType.FOLDER -> FolderTabItem(
                    size = size,
                    tab = tab,
                    selected = tab == selectedDSTab,
                    onClick = { onSelectTab.invoke(tab) }
                )

                BentoDSTabType.UNDERLINE -> UnderlineTabItem(
                    size = size,
                    tab = tab,
                    selected = tab == selectedDSTab,
                    onClick = { onSelectTab.invoke(tab) }
                )
            }
        }
    }
}

@Composable
private fun FolderTabItem(
    size: BentoDSTabSize,
    tab: BentoDSTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val bgColor = if (selected) BentoDSTheme.colors.bg.interactiveOverlay else Color.Transparent
    val textStyle = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.typography.labelMedium
        BentoDSTabSize.LARGE -> BentoDSTheme.typography.labelLarge
    }
    val elementsColor = if (tab.enabled) {
        if (selected) BentoDSTheme.colors.text.interactive
        else BentoDSTheme.colors.fg.primary
    } else BentoDSTheme.colors.fg.disabled

    val verticalPadding = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.dims.x3
        BentoDSTabSize.LARGE -> BentoDSTheme.dims.x4
    }
    Row(
        modifier = Modifier
            .clip(BentoDSTheme.shapes.folderTabShape)
            .selectable(
                selected = selected,
                enabled = tab.enabled,
                role = Role.Tab,
                onClick = onClick,
            )
            .background(
                color = bgColor,
            )
            .padding(
                horizontal = BentoDSTheme.dims.x6,
                vertical = verticalPadding,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.height(24.dp)) {
            tab.iconRes?.let {
                Row {
                    Icon(
                        modifier = Modifier.size(BentoDSTheme.dims.x6),
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = elementsColor,
                    )
                    HSpace(BentoDSTheme.dims.x2)
                }
            }
        }
        Text(
            text = tab.title,
            style = textStyle,
            color = elementsColor,
        )
        if (tab.notificationMarker) {
            HSpace(BentoDSTheme.dims.x2)
            Column(
                modifier = Modifier
                    .size(BentoDSTheme.dims.x2)
                    .background(
                        color = if (tab.enabled) BentoDSTheme.colors.fg.interactive
                        else BentoDSTheme.colors.fg.disabled,
                        shape = BentoDSTheme.shapes.roundShape,
                    ),
            ) {}
        }
    }
}

@Composable
private fun UnderlineTabItem(
    size: BentoDSTabSize,
    tab: BentoDSTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val textStyle = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.typography.labelMedium
        BentoDSTabSize.LARGE -> BentoDSTheme.typography.labelLarge.copy(lineHeight = 20.sp)
    }
    val elementsColor = when {
        !tab.enabled -> BentoDSTheme.colors.fg.disabled
        selected -> BentoDSTheme.colors.text.primary
        else -> BentoDSTheme.colors.text.secondary
    }
    val indicatorColor = BentoDSTheme.colors.outline.inputFocus
    val indicatorHeight = BentoDSTheme.dims.x0_5
    Row(
        modifier = Modifier
            .selectable(
                selected = selected,
                enabled = tab.enabled,
                role = Role.Tab,
                onClick = onClick,
            )
            .drawBehind {
                if (selected) drawRect(
                    color = indicatorColor,
                    topLeft = Offset(0f, this.size.height - indicatorHeight.toPx()),
                    size = Size(this.size.width, indicatorHeight.toPx()),
                )
            }
            .padding(BentoDSTheme.dims.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tab.iconRes?.let {
            Icon(
                modifier = Modifier.size(BentoDSTheme.dims.x5),
                painter = painterResource(it),
                contentDescription = null,
                tint = elementsColor,
            )
            HSpace(BentoDSTheme.dims.x2)
        }
        Text(
            text = tab.title,
            style = textStyle,
            color = elementsColor,
        )
        if (tab.notificationMarker) {
            HSpace(BentoDSTheme.dims.x2)
            Box(
                modifier = Modifier
                    .size(BentoDSTheme.dims.x2)
                    .background(
                        color = if (tab.enabled) BentoDSTheme.colors.fg.interactive
                        else BentoDSTheme.colors.fg.disabled,
                        shape = BentoDSTheme.shapes.roundShape,
                    ),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TabsPreview() {
    val tabsList = remember {
        listOf(
            BentoDSTab(
                title = "Test1",
                notificationMarker = true,
            ),
            BentoDSTab(
                title = "Test2",
                iconRes = R.drawable.ic_house_simple
            ),
            BentoDSTab(
                title = "Test3",
                notificationMarker = true,
                enabled = false,
                iconRes = R.drawable.ic_house_simple,
            ),
        )
    }
    var selectedTab by remember { mutableStateOf(tabsList[0]) }
    BentoDSTheme {
        BentoDSTabs(
            selectedDSTab = selectedTab,
            tabs = tabsList,
            onSelectTab = { tab -> selectedTab = tab },
            content = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UnderlineTabsPreview() {
    val tabsList = remember {
        listOf(BentoDSTab(title = "Active (5)"), BentoDSTab(title = "Archive (2)"))
    }
    var selectedTab by remember { mutableStateOf(tabsList[0]) }
    BentoDSTheme {
        BentoDSTabs(
            selectedDSTab = selectedTab,
            size = BentoDSTabSize.LARGE,
            type = BentoDSTabType.UNDERLINE,
            tabs = tabsList,
            onSelectTab = { tab -> selectedTab = tab },
            content = {}
        )
    }
}
