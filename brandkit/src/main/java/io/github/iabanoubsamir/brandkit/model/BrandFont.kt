package io.github.iabanoubsamir.brandkit.model

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

/**
 * Factory helpers for building [FontFamily] values inside the `typography { }` DSL block.
 *
 * Using `BrandFont` is optional — you can always assign a [FontFamily] directly — but
 * these helpers surface the most common patterns with discoverability via autocomplete.
 *
 * ### Asset font (from res/font/)
 * ```kotlin
 * typography {
 *     fontFamily = BrandFont.asset(
 *         Font(R.font.inter, FontWeight.Normal),
 *         Font(R.font.inter_medium, FontWeight.Medium),
 *         Font(R.font.inter_bold, FontWeight.Bold),
 *     )
 *     displayFontFamily = BrandFont.asset(Font(R.font.playfair_display))
 * }
 * ```
 *
 * ### System font
 * ```kotlin
 * typography {
 *     fontFamily = BrandFont.system(FontFamily.Serif)
 * }
 * ```
 */
object BrandFont {

    /**
     * Creates a [FontFamily] from one or more asset [Font] objects.
     *
     * Each [Font] is typically constructed with a resource ID and an optional weight:
     * ```kotlin
     * Font(R.font.my_font, FontWeight.Normal)
     * ```
     */
    fun asset(vararg fonts: Font): FontFamily = FontFamily(*fonts)

    /**
     * Returns one of the built-in system [FontFamily] values (e.g. [FontFamily.SansSerif],
     * [FontFamily.Serif], [FontFamily.Monospace]).
     */
    fun system(family: FontFamily): FontFamily = family
}
