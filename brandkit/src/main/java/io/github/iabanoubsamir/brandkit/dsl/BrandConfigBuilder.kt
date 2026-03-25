package io.github.iabanoubsamir.brandkit.dsl

import io.github.iabanoubsamir.brandkit.core.BrandConfig

/**
 * Top-level builder that assembles all brand sub-configurations into a [BrandConfig].
 *
 * Do not instantiate directly — use the [io.github.iabanoubsamir.brandkit.brandKit] DSL function.
 */
@BrandKitDsl
class BrandConfigBuilder {

    private val infoBuilder = BrandInfoBuilder()
    private val colorsBuilder = BrandColorsBuilder()
    private var darkColorsBuilder: BrandColorsBuilder? = null
    private val typographyBuilder = BrandTypographyBuilder()
    private val logoBuilder = BrandLogoBuilder()
    private val socialsBuilder = BrandSocialsBuilder()

    /**
     * Configures company information (name, tagline, copyright, website).
     *
     * **Required.** At minimum set [BrandInfoBuilder.name] and [BrandInfoBuilder.copyrightYear].
     */
    fun info(block: BrandInfoBuilder.() -> Unit) {
        infoBuilder.apply(block)
    }

    /**
     * Configures the brand color palette used in light mode (or in both modes when
     * [darkColors] is not called).
     *
     * **Required.** At minimum set [BrandColorsBuilder.primary] and [BrandColorsBuilder.secondary].
     */
    fun colors(block: BrandColorsBuilder.() -> Unit) {
        colorsBuilder.apply(block)
    }

    /**
     * Configures an optional override palette used exclusively in dark mode.
     *
     * You only need to set the fields that differ from the light [colors] block.
     * Fields left at their defaults will fall back to the values from [colors].
     *
     * ```kotlin
     * darkColors {
     *     primary = Color(0xFFBB86FC)
     *     background = Color(0xFF121212)
     *     surface = Color(0xFF1E1E1E)
     *     onBackground = Color.White
     *     onSurface = Color.White
     * }
     * ```
     */
    fun darkColors(block: BrandColorsBuilder.() -> Unit) {
        darkColorsBuilder = (darkColorsBuilder ?: BrandColorsBuilder()).apply(block)
    }

    /**
     * Configures typography — font families and font weights.
     *
     * Optional; all fields have sensible defaults.
     */
    fun typography(block: BrandTypographyBuilder.() -> Unit) {
        typographyBuilder.apply(block)
    }

    /**
     * Configures the brand logo source, shape, and optional dark-mode variant.
     *
     * Optional. When omitted, no logo is rendered.
     */
    fun logo(block: BrandLogoBuilder.() -> Unit) {
        logoBuilder.apply(block)
    }

    /**
     * Configures social media links shown in the footer and social row.
     *
     * Optional. When omitted (or when no links are added), the social section is hidden.
     */
    fun socials(block: BrandSocialsBuilder.() -> Unit) {
        socialsBuilder.apply(block)
    }

    internal fun build(): BrandConfig {
        val lightColors = colorsBuilder.build()
        return BrandConfig(
            info = infoBuilder.build(),
            colors = lightColors,
            darkColors = darkColorsBuilder?.buildWithFallback(lightColors),
            typography = typographyBuilder.build(),
            logo = logoBuilder.build(),
            socials = socialsBuilder.build(),
        )
    }
}
