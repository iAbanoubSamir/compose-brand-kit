package io.github.iabanoubsamir.brandkit.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import io.github.iabanoubsamir.brandkit.core.BrandConfig
import io.github.iabanoubsamir.brandkit.core.LocalBrandConfig

/**
 * Convenience accessor for the current [BrandConfig] within the Compose tree.
 *
 * This is syntactic sugar over [LocalBrandConfig.current] and follows the same pattern
 * as [MaterialTheme.colorScheme], [MaterialTheme.typography], etc.
 *
 * Must be called within a [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Usage
 * ```kotlin
 * @Composable
 * fun MyComposable() {
 *     val brand = MaterialTheme.brand
 *     Text(brand.info.name)
 * }
 * ```
 */
val MaterialTheme.brand: BrandConfig
    @Composable
    @ReadOnlyComposable
    get() = LocalBrandConfig.current
