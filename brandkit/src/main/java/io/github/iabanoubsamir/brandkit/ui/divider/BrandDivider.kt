package io.github.iabanoubsamir.brandkit.ui.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A thin horizontal rule themed to the active brand color scheme.
 *
 * Wraps [HorizontalDivider] with brand-aware defaults. Suitable for separating
 * sections within a screen or inside a card.
 *
 * This composable must be called inside a
 * [io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme] wrapper.
 *
 * ### Usage
 * ```kotlin
 * BrandDivider()
 * BrandDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
 * ```
 *
 * @param modifier Modifier applied to the divider.
 * @param thickness Line thickness. Defaults to `1.dp`.
 * @param color Divider color. Defaults to [MaterialTheme.colorScheme.outlineVariant].
 */
@Composable
fun BrandDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color,
    )
}
