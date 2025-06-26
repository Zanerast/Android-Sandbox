package com.astrick.sandbox.kotlin.lists

import kotlin.system.measureTimeMillis

/**
 * Demonstrates the performance for Kotlin `List`.
 *
 * Example recorded times (in milliseconds):
 * - For list size 1,000,000: 157_375
 * - For list size 10: 33, 31, 32
 */
fun listExample() {
    val list = List(10) { it }

    val listTime = measureTimeMillis {
        repeat(10_000) {
            val result = list
                .map { it * 2 }
                .filter { it % 3 == 0 }
                .sortedDescending()
                .sum()
            check(result != 0)
        }
    }

    println("List time: $listTime ms")
}

/**
 * Demonstrates the performance for Kotlin `Sequence`.
 *
 * Example recorded times (in milliseconds):
 * - For Sequence size 1,000,000: 81_805
 * - For Sequence size 10: 17, 17, 19
 */
fun sequenceExampleThatIsSlowerThanList() {
    // TODO an actual example doesn't exist
    val sequence = List(10) { it }.asSequence()
    val seqTime = measureTimeMillis {
        repeat(10_000) {
            val result = sequence
                .map { it * 2 }
                .filter { it % 3 == 0 }
                .sortedDescending() // Fully materializes sequence
                .sum()
            check(result != 0)
        }
    }
    println("Sequence time: $seqTime ms")
}
