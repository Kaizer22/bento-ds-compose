package com.desh2403.bento_ds_compose.uikit.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

// TODO Folder Tabs + Underlined Tabs + Navigation

enum class BentoDSTabType {
    FOLDER, UNDERLINE
}

enum class BentoDSTabSize {
    MEDIUM, LARGE
}

data class BentoDSTab(
    val title: String,
    val iconRes: Int? = null,
    val enabled: Boolean = true,
    val notificationMarker: Boolean = false,
)

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
    Row {
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
    val selectedBgColor = BentoDSTheme.colors.bg.interactiveOverlay
    val bgColor = remember(selected) {
        if (selected) selectedBgColor
        else Color.Transparent
    }
    val textStyle = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.typography.labelMedium
        BentoDSTabSize.LARGE -> BentoDSTheme.typography.labelLarge
    }
    val elementsColor = if (tab.enabled) {
        if (selected) BentoDSTheme.colors.text.interactive
        else BentoDSTheme.colors.fg.primary
    } else BentoDSTheme.colors.fg.disabled

    val height = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.dims.x12
        BentoDSTabSize.LARGE -> BentoDSTheme.dims.x14
    }
    val verticalPadding = when (size) {
        BentoDSTabSize.MEDIUM -> BentoDSTheme.dims.x3
        BentoDSTabSize.LARGE -> BentoDSTheme.dims.x4
    }
    // TODO контент общий для folder и underline
    Row(
        modifier = Modifier
            //.height(height)
            .clip(BentoDSTheme.shapes.folderTabShape)
            .clickable(
                onClick = {
                    if (tab.enabled) onClick.invoke()
                })
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