package io.github.iabanoubsamir.brandkit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Controls how a brand composable wraps its content — whether it has a background,
 * border, elevation, or no container at all.
 *
 * Apply via composables that accept a `containerStyle` parameter such as
 * [io.github.iabanoubsamir.brandkit.ui.footer.BrandFooter],
 * [io.github.iabanoubsamir.brandkit.ui.social.SocialLinksRow],
 * [io.github.iabanoubsamir.brandkit.ui.copyright.CopyrightBanner], and
 * [io.github.iabanoubsamir.brandkit.ui.info.BrandInfoCard].
 */
enum class BrandContainerStyle {

    /** No wrapping — the content is rendered directly with no background or border. */
    None,

    /**
     * Wraps content in a filled surface using [MaterialTheme.colorScheme.surfaceVariant]
     * with rounded corners. Good for subtle, flat groupings.
     */
    Surface,

    /**
     * Wraps content in an outlined card — a border with no fill or elevation.
     * Clean and minimal; works equally well in light and dark mode.
     */
    Outlined,

    /**
     * Wraps content in an elevated card with a drop shadow.
     * Conveys importance and interactivity.
     */
    Elevated,
}

/**
 * Internal wrapper that applies a [BrandContainerStyle] to arbitrary content.
 *
 * Used by composables that expose a `containerStyle` parameter so they don't
 * each need to duplicate this switching logic.
 */
@Composable
internal fun BrandContainer(
    style: BrandContainerStyle,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    when (style) {
        BrandContainerStyle.None -> Box(modifier = modifier) { content() }

        BrandContainerStyle.Surface -> Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
        ) { content() }

        BrandContainerStyle.Outlined -> OutlinedCard(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
        ) { content() }

        BrandContainerStyle.Elevated -> ElevatedCard(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
        ) { content() }
    }
}
