package com.desh2403.bento_ds_compose.uikit.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.desh2403.bento_ds_compose.uikit.R

private val LexendFontFamily = FontFamily(
    Font(R.font.lexend_thin, FontWeight.W100),
    Font(R.font.lexend_light, FontWeight.W300),
    Font(R.font.lexend_regular, FontWeight.W400),
    Font(R.font.lexend_medium, FontWeight.W500),
    Font(R.font.lexend_semi_bold, FontWeight.W600),
    Font(R.font.lexend_bold, FontWeight.W700),
    Font(R.font.lexend_extra_bold, FontWeight.W800),
)
/**
 * Text styles of Bento DS set in Lexend: display, headline, title, body, strong, link and label,
 * each in large, medium and small sizes.
 *
 * Bento DS: [Typography](https://developer.bento-ds.com/Guides/typography)
 */
data class BentoDSTypography(
    val displayLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 64.sp,
        lineHeight = 72.sp,
        fontWeight = FontWeight.W600,
    ),
    val displayMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 52.sp,
        lineHeight = 58.sp,
        fontWeight = FontWeight.W600,
    ),
    val displaySmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 44.sp,
        lineHeight = 50.sp,
        fontWeight = FontWeight.W600,
    ),

    val headlineLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 40.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.W600,
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.W600,
    ),
    val headlineSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.W600,
    ),

    val titleLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W600,
    ),
    val titleMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W600,
    ),
    val titleSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W600,
    ),

    val bodyLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W400,
    ),
    val bodyLargeStrong: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W600,
    ),
    val bodyMediumStrong: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W600,
    ),
    val bodySmallStrong: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W600,
    ),

    val strongLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W600,
    ),
    val strongMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W600,
    ),
    val strongSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W600,
    ),

    val linkLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
    ),
    val linkMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
    ),
    val linkSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W400,
    ),

    val labelLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W500,
    ),

    val labelLinkLarge: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    ),
    val labelLinkMedium: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500,
    ),
    val labelLinkSmall: TextStyle = TextStyle(
        fontFamily = LexendFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.W500,
    ),
)

internal val LocalBentoDSTypography = staticCompositionLocalOf { BentoDSTypography() }