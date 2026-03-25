# Changelog

All notable changes to compose-brand-kit will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-03-25

### Added
- `brandKit { }` DSL for type-safe brand configuration
- `BrandTheme` — MaterialTheme wrapper with `applyColorScheme` / `applyTypography` flags for host-app compatibility
- `BrandText` sealed class with `Literal` and `Resource` variants for full locale support
- `BrandInfo` — name, tagline, website, copyright year/holder, version name/code
- `BrandColors` — full Material 3 color palette with optional `darkColors` override
- `BrandTypography` — font family and weight overrides via `BrandFont` helpers
- `BrandLogo` — light/dark source, shape clipping (None, Circle, RoundedCorner)
- `BrandSocials` — typed social links with built-in platform icons
- `BrandContainerStyle` — None, Surface, Outlined, Elevated container variants for all composables
- `BrandLogo` composable — auto dark/light source switching
- `CopyrightBanner` — auto year-range computation, centered/start alignment
- `SocialLinksRow` — icons-only or with labels, optional container
- `BrandVersionBadge` — auto-hides when version is blank, build number toggle
- `BrandPoweredBy` — inline logo + customisable prefix, locale-aware via `stringResource`
- `BrandInfoCard` — all-in-one identity card with independently togglable slots
- `BrandFooter` — logo + copyright + socials in a single composable
- `BrandDivider` — brand-tinted `HorizontalDivider`
- Bundled vector icons for Twitter/X, LinkedIn, GitHub, Instagram, Facebook, YouTube, Website, Other
