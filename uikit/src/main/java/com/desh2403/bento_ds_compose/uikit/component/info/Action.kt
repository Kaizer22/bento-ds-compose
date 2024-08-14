package com.desh2403.bento_ds_compose.uikit.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.component.FSpace
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.VSpace
import com.desh2403.bento_ds_compose.uikit.component.button.BentoDSButton
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonSize
import com.desh2403.bento_ds_compose.uikit.component.button.ButtonType
import com.desh2403.bento_ds_compose.uikit.component.loading.InlineLoader
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

data class ActionBannerInfo(
    val bannerType: BentoDSBannerType,
    val bannerText: String,
    val isFillMaxWidth: Boolean,
)

@Composable
fun Action(
    banner: ActionBannerInfo? = null,
    actionButtonType: ButtonType = ButtonType.PRIMARY_SOLID,
    buttonSize: ButtonSize,
    isLoading: Boolean,
    loaderText: String,
    cancelButtonText: String,
    onCancelClick: () -> Unit,
    actionButtonText: String,
    onActionClick: () -> Unit,
) {
    Column {
        banner?.let {
            Row {
                FSpace()
                BentoDSBanner(
                    bannerType = banner.bannerType,
                    description = banner.bannerText,
                    isFillMaxWidth = banner.isFillMaxWidth,
                )
            }
            VSpace(h = BentoDSTheme.dimensions.x2)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FSpace()
            if (isLoading) {
                InlineLoader(
                    text = loaderText,
                )
                HSpace(w = BentoDSTheme.dimensions.x4)
            }
            BentoDSButton(
                text = cancelButtonText,
                isEnabled = !isLoading,
                buttonType = ButtonType.SECONDARY_TRANSPARENT,
                onClick = onCancelClick,
            )
            HSpace(w = BentoDSTheme.dimensions.x4)
            BentoDSButton(
                text = actionButtonText,
                buttonSize = buttonSize,
                buttonType = actionButtonType,
                isEnabled = !isLoading,
                onClick = onActionClick,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ActionPreview() {
    BentoDSTheme {
        Column {
            Action(
                banner = ActionBannerInfo(
                    bannerType = BentoDSBannerType.NEGATIVE,
                    bannerText = "Text",
                    isFillMaxWidth = false,
                ),
                buttonSize = ButtonSize.M,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dimensions.x4)
            Action(
                banner = ActionBannerInfo(
                    bannerType = BentoDSBannerType.NEGATIVE,
                    bannerText = "Text",
                    isFillMaxWidth = false,
                ),
                buttonSize = ButtonSize.L,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dimensions.x4)
            Action(
                banner = ActionBannerInfo(
                    bannerType = BentoDSBannerType.NEGATIVE,
                    bannerText = "Text",
                    isFillMaxWidth = true,
                ),
                buttonSize = ButtonSize.L,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dimensions.x4)
            Action(
                buttonSize = ButtonSize.L,
                isLoading = true,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dimensions.x4)
            Action(
                buttonSize = ButtonSize.L,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dimensions.x4)
            Action(
                buttonSize = ButtonSize.L,
                actionButtonType = ButtonType.DANGER_SOLID,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
        }
    }
}