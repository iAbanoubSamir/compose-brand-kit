package io.github.iabanoubsamir.brandkit.core

import androidx.compose.runtime.compositionLocalOf

/**
 * Composition local that carries the active [BrandConfig] down the Compose tree.
 *
 * Never access this directly in application code. Instead, read brand values
 * through the provided composables (`BrandLogo`, `BrandFooter`, etc.) or via
 * `LocalBrandConfig.current` inside custom composables that need brand data.
 *
 * This local has no default value — attempting to read it outside of a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper will throw
 * an [IllegalStateException] with a descriptive message.
 */
val LocalBrandConfig = compositionLocalOf<BrandConfig> {
    error(
        "No BrandConfig found in the composition. " +
            "Wrap your content with BrandTheme(brandConfig) { … } to provide one."
    )
}
