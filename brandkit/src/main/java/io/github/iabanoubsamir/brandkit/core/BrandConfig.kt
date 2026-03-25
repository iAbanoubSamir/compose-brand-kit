package io.github.iabanoubsamir.brandkit.core

import io.github.iabanoubsamir.brandkit.model.BrandColors
import io.github.iabanoubsamir.brandkit.model.BrandInfo
import io.github.iabanoubsamir.brandkit.model.BrandLogo
import io.github.iabanoubsamir.brandkit.model.BrandSocials
import io.github.iabanoubsamir.brandkit.model.BrandTypography

/**
 * The single source of truth for a brand's visual and textual identity.
 *
 * Create an instance using the [io.github.iabanoubsamir.brandkit.brandKit] DSL function
 * and pass it to [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] to make it
 * available throughout the Compose tree via [LocalBrandConfig].
 *
 * ### Minimal example
 * ```kotlin
 * val myBrand = brandKit {
 *     info {
 *         name = "GitHub"
 *         tagline = "Where the world builds software"
 *         website = "https://github.com"
 *         copyrightYear = 2008
 *         copyrightHolder = "GitHub, Inc."
 *     }
 *     colors {
 *         primary = Color(0xFF0969DA)
 *         secondary = Color(0xFF238636)
 *     }
 * }
 *
 * BrandTheme(myBrand) { /* your app content */ }
 * ```
 *
 * ### With distinct dark colors
 * ```kotlin
 * val myBrand = brandKit {
 *     colors {
 *         primary = Color(0xFF0969DA)
 *         secondary = Color(0xFF238636)
 *     }
 *     darkColors {           // optional — overrides specific fields in dark mode
 *         primary = Color(0xFF58A6FF)
 *         secondary = Color(0xFF3FB950)
 *         background = Color(0xFF0D1117)
 *         surface = Color(0xFF161B22)
 *     }
 * }
 * ```
 *
 * @property info Company name, tagline, website, and copyright details.
 * @property colors The brand's color palette for light mode (also used in dark mode when
 *   [darkColors] is `null`).
 * @property darkColors Optional palette that overrides [colors] when the device is in dark mode.
 *   Only the fields you set in the DSL are changed; everything else falls back to [colors].
 * @property typography Font families and weights that shape the type scale.
 * @property logo Logo image source(s), shape, and optional dark-mode variant.
 * @property socials Social media links shown in footer and social rows.
 */
data class BrandConfig(
    val info: BrandInfo,
    val colors: BrandColors,
    val darkColors: BrandColors? = null,
    val typography: BrandTypography = BrandTypography(),
    val logo: BrandLogo = BrandLogo(),
    val socials: BrandSocials = BrandSocials(),
) {
    /** Returns [darkColors] when [isDark] is `true` and dark colors are configured; otherwise [colors]. */
    fun resolveColors(isDark: Boolean): BrandColors =
        if (isDark && darkColors != null) darkColors else colors
}
