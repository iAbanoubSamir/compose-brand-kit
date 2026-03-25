package io.github.iabanoubsamir.brandkit.dsl

/**
 * DSL marker for the compose-brand-kit builder API.
 *
 * Applying this annotation to every builder class prevents accidentally calling
 * an outer builder's function from within an inner builder block — a common
 * source of confusing DSL bugs.
 */
@DslMarker
annotation class BrandKitDsl
