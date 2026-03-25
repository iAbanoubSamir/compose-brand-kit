package io.github.iabanoubsamir.brandkit.model

/**
 * Defines the clipping shape applied to a brand logo image.
 *
 * Passed through [BrandLogo.shape] and applied by the `BrandLogo` composable at render time.
 */
sealed class BrandLogoShape {

    /** No clipping — renders the logo exactly as supplied. */
    data object None : BrandLogoShape()

    /** Clips the logo to a perfect circle (aspect-ratio 1:1 recommended). */
    data object Circle : BrandLogoShape()

    /**
     * Clips the logo to a rounded rectangle.
     *
     * @property cornerPercent Corner radius as a percentage of the shorter side (0–50).
     *   `50` produces a pill / stadium shape; `0` is equivalent to [None].
     */
    data class RoundedCorner(val cornerPercent: Int = 20) : BrandLogoShape()
}
