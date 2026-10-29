package com.desh2403.bento_ds_compose.uikit.component.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.desh2403.bento_ds_compose.uikit.R
import com.desh2403.bento_ds_compose.uikit.component.HSpace
import com.desh2403.bento_ds_compose.uikit.component.info.BentoDSIcon
import com.desh2403.bento_ds_compose.uikit.component.info.IconSize
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

private enum class StepState {
    TODO, IN_PROGRESS, DONE,
}

/**
 * Horizontal list of numbered steps; finished steps show [doneIcon] and the current step is highlighted.
 * The list scrolls to the current step.
 *
 * Bento DS: [Stepper](https://developer.bento-ds.com/Components/Stepper)
 *
 * Extensions over Bento DS: [doneIcon].
 *
 * @param modifier modifier of the stepper.
 * @param steps labels of the steps. Bento DS `steps`.
 * @param currentStep 0-based index of the current step. Bento DS `currentStep`.
 * @param doneIcon icon of a finished step.
 */
@Composable
fun Stepper(
    modifier: Modifier = Modifier,
    steps: List<String>,
    currentStep: Int,
    @DrawableRes doneIcon: Int = R.drawable.ic_positive_solid,
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(currentStep, steps.size) {
        if (currentStep in steps.indices) scrollState.scrollToItem(currentStep)
    }
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState
    ) {
        itemsIndexed(steps) { index, step ->
            val state = when {
                currentStep == index -> StepState.IN_PROGRESS
                currentStep > index -> StepState.DONE
                else -> StepState.TODO
            }
            Step(
                number = index + 1,
                label = step,
                state = state,
                doneIcon = doneIcon,
            )
            if (index != steps.size - 1) {
                HSpace(w = BentoDSTheme.dims.x10)
            }
        }
    }

}

@Composable
private fun Step(
    number: Int,
    label: String,
    state: StepState,
    @DrawableRes doneIcon: Int,
) {
    val labelColor = when (state) {
        StepState.TODO -> BentoDSTheme.colors.text.primary
        StepState.IN_PROGRESS -> BentoDSTheme.colors.text.interactive
        StepState.DONE -> BentoDSTheme.colors.text.secondary
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StepIcon(state = state, number = number, doneIcon = doneIcon)
        HSpace(w = BentoDSTheme.dims.x2)
        Text(
            text = label,
            style = BentoDSTheme.typography.labelLarge,
            color = labelColor
        )
    }
}

@Composable
private fun StepIcon(
    state: StepState,
    number: Int,
    @DrawableRes doneIcon: Int,
) {
    when (state) {
        StepState.DONE -> {
            BentoDSIcon(
                modifier = Modifier.padding(
                    BentoDSTheme.dims.x1
                ),
                iconSize = IconSize.L,
                iconRes = doneIcon,
                iconTint = BentoDSTheme.colors.fg.secondary,
            )
        }

        StepState.TODO -> {
            Box(
                modifier = Modifier
                    .size(
                        BentoDSTheme.dims.x8
                    )
                    .background(
                        color = BentoDSTheme.colors.bg.overlay,
                        shape = BentoDSTheme.shapes.roundShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = number.toString(),
                    color = BentoDSTheme.colors.text.primary,
                    style = BentoDSTheme.typography.labelLarge,
                )
            }
        }

        StepState.IN_PROGRESS -> {
            Box(
                modifier = Modifier
                    .size(
                        BentoDSTheme.dims.x8
                    )
                    .background(
                        color = BentoDSTheme.colors.bg.interactive,
                        shape = BentoDSTheme.shapes.roundShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = number.toString(),
                    color = BentoDSTheme.colors.text.primaryStaticLight,
                    style = BentoDSTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StepperPreview() {
    BentoDSTheme {
        Stepper(
            steps = listOf(
                "Label",
                "Label",
                "Label",
                "Label",
                "Label",
            ),
            currentStep = 2,
        )
    }
}