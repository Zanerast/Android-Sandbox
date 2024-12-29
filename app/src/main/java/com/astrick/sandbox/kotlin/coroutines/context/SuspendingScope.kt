package com.astrick.sandbox.kotlin.coroutines.context

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * Demonstrates the use of `coroutineScope` to structure concurrency.
 *
 * Key observations:
 * - If any child coroutine within the `coroutineScope` fails, the entire scope will fail, canceling all its children.
 * - The last task starts only after the `coroutineScope` block finishes executing.
 * - `coroutineScope` automatically manages the lifecycle of coroutines within it, ensuring structured concurrency.
 */
@Suppress("unused")
fun suspendingScopeExample() = runBlocking {
    coroutineScope {
        launch {
            println("Starting task 1")
            delay(500.milliseconds)
            println("Task 1 completed")
        }
        launch {
            println("Starting task 2")
            delay(300.milliseconds)
            println("Task 2 completed")
        }
    }
    // Task 3 will wait for coroutineScope to finish before starting
    launch {
        println("Starting task 3")
        delay(100.milliseconds)
        println("Task 2 completed")
    }
    Thread.sleep(1_000)
}
