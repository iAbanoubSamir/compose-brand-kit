# Contributing to compose-brand-kit

Thank you for your interest in contributing! Here's everything you need to get started.

## Getting Started

1. **Fork** the repository and clone your fork.
2. Open the project in **Android Studio** (Meerkat or newer recommended).
3. Let Gradle sync complete — no extra setup required.
4. Run the sample app on an emulator or device to verify your environment.

## Project Structure

```
compose-brand-kit/
├── brandkit/   ← the library (this is what you're contributing to)
└── app/        ← sample app that demos every composable
```

Changes to public API must be reflected in the sample app's `DemoHomeScreen`.

## Branches

| Branch | Purpose |
|--------|---------|
| `main` | Stable, release-ready code |
| `dev`  | Active development — open PRs here |

## How to Contribute

### Bug fixes
1. Open an issue describing the bug before sending a PR.
2. Include a minimal repro if possible.
3. Reference the issue number in your PR title: `Fix #42 — CopyrightBanner year range`.

### New features
1. Open an issue to discuss the feature first — this avoids wasted effort.
2. New composables must follow the existing patterns:
   - Accept `modifier: Modifier = Modifier` as the first parameter.
   - Accept `containerStyle: BrandContainerStyle = BrandContainerStyle.None` where applicable.
   - Accept `centered: Boolean = true` where applicable.
   - Read brand config exclusively from `LocalBrandConfig.current`.
   - Include at least one `@Preview` composable.
   - Add KDoc with a usage example.

### DSL additions
- New builder properties must remain backward-compatible.
- String fields that appear in UI must use `BrandText` (not `String`) for locale support.

## Code Style

- Follow the existing code style — no extra lint rules to configure.
- No `TODO` comments — either implement it or leave it out.
- No debug log statements (`Log.d`, `println`).
- No hardcoded strings in the library — use parameters or `BrandText`.

## Submitting a Pull Request

1. Make sure the project builds: `./gradlew :brandkit:assembleRelease :app:assembleDebug`
2. Verify Compose previews render correctly in Android Studio.
3. Write a clear PR description explaining **what** changed and **why**.
4. Keep PRs focused — one feature or fix per PR.

## Reporting Issues

Use [GitHub Issues](../../issues). Include:
- Android / API level
- Library version
- Minimal code snippet that reproduces the problem
- Expected vs actual behaviour

## License

By contributing, you agree that your contributions will be licensed under the [MIT License](LICENSE).
