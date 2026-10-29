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
import com.desh2403.bento_ds_compose.uikit.component.loading.BentoDSInlineLoader
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

/**
 * Banner shown above the buttons of an [Action].
 *
 * @param bannerType status of the banner.
 * @param bannerText text of the banner.
 * @param isFillMaxWidth whether the banner takes the full width. Bento DS `errorBannerWidth = "fill"`.
 */
data class ActionBannerInfo(
    val bannerType: BentoDSBannerType,
    val bannerText: String,
    val isFillMaxWidth: Boolean,
)

/**
 * Group of a secondary (cancel) and a primary action, aligned to the end, with an optional banner
 * above and an inline loader while the action is in progress.
 *
 * Bento DS: [Actions](https://developer.bento-ds.com/Components/Actions)
 *
 * Differences from Bento DS:
 * - the secondary action is required;
 * - [banner] accepts any [BentoDSBannerType], while Bento DS shows only an error banner;
 * - the primary action style is set by [actionButtonType] instead of `isDestructive`.
 *
 * @param banner banner above the buttons. Bento DS `error` and `errorBannerWidth`.
 * @param actionButtonType style of the primary button.
 * @param buttonSize size of both buttons. Bento DS `size`.
 * @param isLoading shows the loader and disables both buttons.
 * @param loaderText text next to the loader. Bento DS `loadingMessage`.
 * @param cancelButtonText label of the secondary button. Bento DS `secondaryAction.label`.
 * @param onCancelClick called when the secondary button is clicked. Bento DS `secondaryAction.onPress`.
 * @param actionButtonText label of the primary button. Bento DS `primaryAction.label`.
 * @param onActionClick called when the primary button is clicked. Bento DS `primaryAction.onPress`.
 */
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
            VSpace(h = BentoDSTheme.dims.x2)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FSpace()
            if (isLoading) {
                BentoDSInlineLoader(
                    text = loaderText,
                )
                HSpace(w = BentoDSTheme.dims.x4)
            }
            BentoDSButton(
                text = cancelButtonText,
                buttonSize = buttonSize,
                enabled = !isLoading,
                buttonType = ButtonType.SECONDARY_TRANSPARENT,
                onClick = onCancelClick,
            )
            HSpace(w = BentoDSTheme.dims.x4)
            BentoDSButton(
                text = actionButtonText,
                buttonSize = buttonSize,
                buttonType = actionButtonType,
                enabled = !isLoading,
                onClick = onActionClick,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ActionPreview() {
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
            VSpace(h = BentoDSTheme.dims.x4)
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
            VSpace(h = BentoDSTheme.dims.x4)
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
            VSpace(h = BentoDSTheme.dims.x4)
            Action(
                buttonSize = ButtonSize.L,
                isLoading = true,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dims.x4)
            Action(
                buttonSize = ButtonSize.L,
                isLoading = false,
                loaderText = "Loading",
                cancelButtonText = "Cancel",
                onCancelClick = {},
                actionButtonText = "Action",
                onActionClick = {},
            )
            VSpace(h = BentoDSTheme.dims.x4)
            Action(
                buttonSize = ButtonSize.L,
                actionButtonType = ButtonType.NEGATIVE_SOLID,
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