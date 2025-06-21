package com.astrick.sandbox.kotlin.lists

import kotlin.system.measureTimeMillis

/**
 * Demonstrates the performance for Kotlin `List`.
 *
 * Example recorded times (in milliseconds):
 * - For list size 1,000,000: 173, 205, 231, 136
 * - For list size 10: 48, 83, 85, 47, 85
 */
fun listExample() {
    val list = List(1_000_000) { it }

    val listTime = measureTimeMillis {
        val result = list
            .map { it * 2 }
            .filter { it % 3 == 0 }
            .sortedDescending()
            .sum()
        println("List result: $result")
    }

    println("List time: $listTime ms")
}

/**
 * Demonstrates the performance for Kotlin `Sequence`.
 *
 * Note:
 * Sequence still appears faster than list for small sizes.
 *
 * Example recorded times (in milliseconds):
 * - For list size 1,000,000: 203, 242, 118, 291
 * - For list size 10: 46, 80, 55, 54
 */
fun sequenceExampleThatIsSlowerThanList() {
    val list = List(1_000_000) { it }
    val seqTime = measureTimeMillis {
        val result = list
            .asSequence()
            .map { it * 2 }
            .filter { it % 3 == 0 }
            .sortedDescending() // Fully materializes sequence
            .sum()
        println("Sequence result: $result")
    }
    println("Sequence time: $seqTime ms")
}
