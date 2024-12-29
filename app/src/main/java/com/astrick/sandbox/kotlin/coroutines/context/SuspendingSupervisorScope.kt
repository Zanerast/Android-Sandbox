package com.astrick.sandbox.kotlin.coroutines.context

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.milliseconds

/**
 * Demonstrates the use of `supervisorScope` to structure concurrency.
 *
 * Key observations:
 * - If any child coroutine within the `supervisorScope` fails, the other children will continue.
 * - The last task starts only after the `supervisorScope` block finishes executing.
 * - `supervisorScope` automatically manages the lifecycle of coroutines within it, ensuring structured concurrency.
 */
@Suppress("unused")
fun suspendingSupervisorScopeExample() = runBlocking {
    supervisorScope {
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
    // Task 3 will wait for supervisorScope to finish before starting
    launch {
        println("Starting task 3")
        delay(100.milliseconds)
        println("Task 3 completed")
    }
    Thread.sleep(1_000)
}
