package com.astrick.sandbox.kotlin.inline

import kotlin.system.measureTimeMillis

/**
 * Benchmarks the performance difference between inline and noinline functions
 * when the lambda does not capture external variables.
 *
 * Example output:
 * Inline time:   2 ms
 * Noinline time: 9 ms
 *
 * Explanation:
 * In this case, the lambda is small and does not capture any external variables.
 * The inline function avoids allocating a function object on each call,
 * leading to reduced overhead and better performance in tight loops.
 */
fun main() {
    val iterations = 1_000_000

    val inlineTime = measureTimeMillis {
        repeat(iterations) {
            inlineFunction { val x = it * 2 }
        }
    }

    val noinlineTime = measureTimeMillis {
        repeat(iterations) {
            noinlineFunction { val x = it * 2 }
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
