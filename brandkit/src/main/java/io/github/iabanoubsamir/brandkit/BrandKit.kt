package io.github.iabanoubsamir.brandkit

import io.github.iabanoubsamir.brandkit.core.BrandConfig
import io.github.iabanoubsamir.brandkit.dsl.BrandConfigBuilder

/**
 * Entry point for the compose-brand-kit library.
 *
 * Constructs a [BrandConfig] using a type-safe Kotlin DSL. The resulting object is
 * immutable and safe to store as a top-level `val` or in a singleton.
 *
 * ### Minimal example
 * ```kotlin
 * val myBrand = brandKit {
 *     info {
 *         name = "GitHub"
 *         tagline = "Where the world builds software"
 *         website = "https://github.com"
 *         copyrightYear = 2008
 *     }
 *     colors {
 *         primary = Color(0xFF0969DA)
 *         secondary = Color(0xFF238636)
 *     }
 * }
 * ```
 *
 * ### Full example
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
 *         onPrimary = Color.White
 *         primaryContainer = Color(0xFFDBEAFF)
 *         onPrimaryContainer = Color(0xFF002D6E)
 *         secondary = Color(0xFF238636)
 *         secondaryContainer = Color(0xFFD2F4DB)
 *         onSecondaryContainer = Color(0xFF003D10)
 *     }
 *     typography {
 *         fontFamily = FontFamily.SansSerif
 *         headlineFontWeight = FontWeight.Bold
 *     }
 *     logo {
 *         fromResource(R.drawable.logo_light)
 *         darkFromResource(R.drawable.logo_dark)
 *         contentDescription = "GitHub logo"
 *     }
 *     socials {
 *         link(SocialPlatform.GITHUB, "https://github.com")
 *         link(SocialPlatform.LINKEDIN, "https://linkedin.com/company/github")
 *         link(SocialPlatform.TWITTER, "https://twitter.com/github")
 *     }
 * }
 * ```
 *
 * Pass the resulting [BrandConfig] to
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] to make all brand composables
 * available in the composition tree.
 *
 * @param block DSL configuration block.
 * @return An immutable [BrandConfig] ready for use with [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme].
 */
fun brandKit(block: BrandConfigBuilder.() -> Unit): BrandConfig =
    BrandConfigBuilder().apply(block).build()
