package io.github.iabanoubsamir.brandkit.model

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography settings that define a brand's font personality.
 *
 * Rather than requiring a full Material 3 [Typography] object up-front, this class
 * accepts high-level font choices that are automatically expanded into a complete
 * type scale via [toMaterial3Typography].
 *
 * @property fontFamily The default font family used across body, label, and title styles.
 * @property displayFontFamily An optional separate family for large display text.
 *   Falls back to [fontFamily] when `null`.
 * @property headlineFontWeight Font weight applied to headline styles (H1–H3 equivalent).
 * @property titleFontWeight Font weight applied to title styles (e.g. app bar titles).
 * @property bodyFontWeight Font weight applied to body copy.
 * @property labelFontWeight Font weight applied to small label / caption text.
 */
data class BrandTypography(
    val fontFamily: FontFamily = FontFamily.Default,
    val displayFontFamily: FontFamily? = null,
    val headlineFontWeight: FontWeight = FontWeight.Normal,
    val titleFontWeight: FontWeight = FontWeight.Medium,
    val bodyFontWeight: FontWeight = FontWeight.Normal,
    val labelFontWeight: FontWeight = FontWeight.Medium,
)

/**
 * Expands this [BrandTypography] into a Material 3 [Typography] type scale.
 *
 * Every style inherits [BrandTypography.fontFamily] (or [BrandTypography.displayFontFamily]
 * for display sizes), with sizes and line heights matching the Material 3 baseline spec.
 */
fun BrandTypography.toMaterial3Typography(): Typography {
    val display = displayFontFamily ?: fontFamily
    return Typography(
        displayLarge = TextStyle(
            fontFamily = display,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = display,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = display,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = headlineFontWeight,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = headlineFontWeight,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = headlineFontWeight,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = titleFontWeight,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = titleFontWeight,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = titleFontWeight,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = bodyFontWeight,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = bodyFontWeight,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = bodyFontWeight,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = labelFontWeight,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = labelFontWeight,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = labelFontWeight,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),
    )
}
