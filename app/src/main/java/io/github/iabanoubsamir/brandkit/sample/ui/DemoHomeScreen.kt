package io.github.iabanoubsamir.brandkit.sample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.iabanoubsamir.brandkit.sample.SampleBrand
import io.github.iabanoubsamir.brandkit.ui.BrandContainerStyle
import io.github.iabanoubsamir.brandkit.ui.copyright.CopyrightBanner
import io.github.iabanoubsamir.brandkit.ui.divider.BrandDivider
import io.github.iabanoubsamir.brandkit.ui.footer.BrandFooter
import io.github.iabanoubsamir.brandkit.ui.info.BrandInfoCard
import io.github.iabanoubsamir.brandkit.ui.logo.BrandLogo
import io.github.iabanoubsamir.brandkit.ui.poweredby.BrandPoweredBy
import io.github.iabanoubsamir.brandkit.ui.social.SocialLinksRow
import io.github.iabanoubsamir.brandkit.ui.theme.BrandTheme
import io.github.iabanoubsamir.brandkit.ui.version.BrandVersionBadge

/**
 * Demo screen showcasing every compose-brand-kit composable with labeled variants.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoHomeScreen(
    darkMode: Boolean,
    onToggleDarkMode: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "compose-brand-kit",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                actions = {
                    IconButton(onClick = onToggleDarkMode) {
                        Icon(
                            imageVector = if (darkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = if (darkMode) "Switch to light" else "Switch to dark",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                scrollBehavior = scrollBehavior,
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            item { Spacer(Modifier.height(4.dp)) }

            // ── BrandInfoCard ──────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "BrandInfoCard",
                    subtitle = "Compose name, tagline, version, copyright, and socials. Each slot is independently toggleable.",
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    VariantLabel("Full — outlined, with socials and labels")
                    BrandInfoCard(
                        modifier = Modifier.fillMaxWidth(),
                        showSocials = true,
                        showSocialLabels = true,
                        containerStyle = BrandContainerStyle.Outlined,
                    )
                    VariantLabel("Compact — name and version only")
                    BrandInfoCard(
                        modifier = Modifier.fillMaxWidth(),
                        showLogo = false,
                        showTagline = false,
                        showCopyright = false,
                        containerStyle = BrandContainerStyle.Surface,
                    )
                    VariantLabel("Start aligned — elevated, icons only")
                    BrandInfoCard(
                        modifier = Modifier.fillMaxWidth(),
                        centered = false,
                        showSocials = true,
                        containerStyle = BrandContainerStyle.Elevated,
                    )
                }
            }

            // ── BrandLogo ──────────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "BrandLogo",
                    subtitle = "Auto-switches to darkSource in dark mode. Supports None, Circle, and RoundedCorner shapes.",
                )
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            BrandLogo(modifier = Modifier.size(56.dp))
                            VariantLabel("Default")
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            BrandLogo(modifier = Modifier.size(80.dp))
                            VariantLabel("Large")
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            BrandLogo(modifier = Modifier.size(40.dp))
                            VariantLabel("Small")
                        }
                    }
                }
            }

            // ── SocialLinksRow ─────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "SocialLinksRow",
                    subtitle = "Icons only, icons with labels, or wrapped in a surface container.",
                )
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        VariantLabel("Icons only", padded = true)
                        SocialLinksRow(modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("With labels", padded = true)
                        SocialLinksRow(
                            showLabels = true,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp),
                        )
                        BrandDivider()
                        VariantLabel("With labels + surface container", padded = true)
                        SocialLinksRow(
                            showLabels = true,
                            containerStyle = BrandContainerStyle.Surface,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp),
                        )
                    }
                }
            }

            // ── CopyrightBanner ────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "CopyrightBanner",
                    subtitle = "Auto-computes year range from the configured copyright year. Center or start aligned.",
                )
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        VariantLabel("Default", padded = true)
                        CopyrightBanner(modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("Surface container", padded = true)
                        CopyrightBanner(
                            containerStyle = BrandContainerStyle.Surface,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                        BrandDivider()
                        VariantLabel("Start aligned — outlined container", padded = true)
                        CopyrightBanner(
                            centered = false,
                            containerStyle = BrandContainerStyle.Outlined,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp),
                        )
                    }
                }
            }

            // ── BrandVersionBadge ──────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "BrandVersionBadge",
                    subtitle = "Reads version from brand config. Auto-hides when no version is set.",
                )
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        VariantLabel("Default — surface pill", padded = true)
                        BrandVersionBadge(modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("With build number", padded = true)
                        BrandVersionBadge(showBuildNumber = true, modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("Outlined container", padded = true)
                        BrandVersionBadge(
                            containerStyle = BrandContainerStyle.Outlined,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                        BrandDivider()
                        VariantLabel("Plain text — no container", padded = true)
                        BrandVersionBadge(
                            containerStyle = BrandContainerStyle.None,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                    }
                }
            }

            // ── BrandPoweredBy ─────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "BrandPoweredBy",
                    subtitle = "Attribution badge for apps built on top of a platform. Prefix is fully customizable.",
                )
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        VariantLabel("Default — centered, with logo", padded = true)
                        BrandPoweredBy(modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("No logo", padded = true)
                        BrandPoweredBy(showLogo = false, modifier = Modifier.padding(bottom = 16.dp))
                        BrandDivider()
                        VariantLabel("Custom prefix — surface container", padded = true)
                        BrandPoweredBy(
                            prefix = "Built with",
                            containerStyle = BrandContainerStyle.Surface,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                        BrandDivider()
                        VariantLabel("Start aligned — outlined container", padded = true)
                        BrandPoweredBy(
                            centered = false,
                            containerStyle = BrandContainerStyle.Outlined,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp),
                        )
                    }
                }
            }

            // ── BrandFooter ────────────────────────────────────────────────────
            item {
                SectionHeader(
                    title = "BrandFooter",
                    subtitle = "Combines logo, copyright, and socials. Configure each slot, alignment, and icon style.",
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    VariantLabel("Default — centered, icons only")
                    BrandFooter(
                        modifier = Modifier.fillMaxWidth(),
                        containerStyle = BrandContainerStyle.Outlined,
                    )
                    Spacer(Modifier.height(12.dp))
                    VariantLabel("No logo — with social labels")
                    BrandFooter(
                        modifier = Modifier.fillMaxWidth(),
                        showLogo = false,
                        showSocialLabels = true,
                        containerStyle = BrandContainerStyle.Surface,
                    )
                    Spacer(Modifier.height(12.dp))
                    VariantLabel("Start aligned — icons only")
                    BrandFooter(
                        modifier = Modifier.fillMaxWidth(),
                        centered = false,
                        containerStyle = BrandContainerStyle.Outlined,
                    )
                }
            }

            item { Spacer(Modifier.height(4.dp)) }
        }
    }
}

// ── Helpers ───────────────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun VariantLabel(text: String, padded: Boolean = false) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = if (padded) {
            Modifier.padding(horizontal = 16.dp).padding(top = 16.dp, bottom = 8.dp)
        } else {
            Modifier.padding(bottom = 4.dp)
        },
    )
}

@Preview(showBackground = true, name = "Demo Home — Light")
@Composable
private fun DemoHomeScreenLightPreview() {
    BrandTheme(SampleBrand, darkTheme = false) {
        DemoHomeScreen(darkMode = false, onToggleDarkMode = {})
    }
}

@Preview(showBackground = true, name = "Demo Home — Dark")
@Composable
private fun DemoHomeScreenDarkPreview() {
    BrandTheme(SampleBrand, darkTheme = true) {
        DemoHomeScreen(darkMode = true, onToggleDarkMode = {})
    }
}
