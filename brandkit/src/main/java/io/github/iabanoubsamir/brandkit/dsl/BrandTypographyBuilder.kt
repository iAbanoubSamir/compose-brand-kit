package io.github.iabanoubsamir.brandkit.dsl

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.iabanoubsamir.brandkit.model.BrandTypography

/**
 * Builder for [BrandTypography]. Use inside the `typography { }` block of [BrandConfigBuilder].
 *
 * ### System font
 * ```kotlin
 * typography {
 *     fontFamily = BrandFont.system(FontFamily.Serif)
 *     headlineFontWeight = FontWeight.Bold
 * }
 * ```
 *
 * ### Asset font (from res/font/)
 * ```kotlin
 * typography {
 *     fontFamily = BrandFont.asset(
 *         Font(R.font.inter, FontWeight.Normal),
 *         Font(R.font.inter_bold, FontWeight.Bold),
 *     )
 *     displayFontFamily = BrandFont.asset(Font(R.font.playfair_display))
 * }
 * ```
 */
@BrandKitDsl
class BrandTypographyBuilder {

    /** The default font family used across all text styles. */
    var fontFamily: FontFamily = FontFamily.Default

    /**
     * An optional separate font family for large display text.
     * Falls back to [fontFamily] when `null`.
     */
    var displayFontFamily: FontFamily? = null

    /** Font weight applied to headline styles. */
    var headlineFontWeight: FontWeight = FontWeight.Normal

    /** Font weight applied to title styles. */
    var titleFontWeight: FontWeight = FontWeight.Medium

    /** Font weight applied to body copy. */
    var bodyFontWeight: FontWeight = FontWeight.Normal

    /** Font weight applied to small label / caption text. */
    var labelFontWeight: FontWeight = FontWeight.Medium

    internal fun build(): BrandTypography = BrandTypography(
        fontFamily = fontFamily,
        displayFontFamily = displayFontFamily,
        headlineFontWeight = headlineFontWeight,
        titleFontWeight = titleFontWeight,
        bodyFontWeight = bodyFontWeight,
        labelFontWeight = labelFontWeight,
    )
}
