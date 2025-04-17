package com.astrick.sandbox.kotlin.inline

import kotlin.system.measureTimeMillis

/**
 * Demonstrates a case where an inline function performs worse than a noinline function.
 *
 * Example output:
 * Inline time:   116 ms
 * Noinline time: 60 ms
 *
 * Explanation:
 * When a lambda captures an external variable (like `capturedValue`),
 * the compiler must inline both the lambda body and its capture context
 * at every call site. In tight loops, this leads to repeated code generation,
 * increased bytecode size, and worse CPU cache performance.
 *
 * The noinline version reuses a single lambda instance with its captured reference,
 * avoiding that overhead.
 */
fun main() {
    val iterations = 1_000_000
    val capturedValue = "This is a captured string"

    val inlineTime = measureTimeMillis {
        repeat(iterations) {
            inlineFunction { capturedValue.reversed() }
        }
    }

    val noinlineTime = measureTimeMillis {
        repeat(iterations) {
            noinlineFunction { capturedValue.reversed() }
        }
    }

    println("Inline time:   $inlineTime ms")
    println("Noinline time: $noinlineTime ms")
}

private inline fun inlineFunction(action: () -> Unit) {
    action()
}

private fun noinlineFunction(action: () -> Unit) {
    action()
}
