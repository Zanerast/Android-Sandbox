package com.astrick.sandbox.kotlin.coroutines.context

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Demonstrates the behavior of jobs and exception handling in coroutines.
 *
 * This example shows:
 * - How a failing child coroutine cancels the parent scope, leading to the cancellation of sibling coroutines.
 *
 * Key observations:
 * - If one coroutine throws an exception, the parent scope is cancelled, affecting sibling coroutines.
 * - Coroutines in a cancelled scope do not execute.
 */
@Suppress("unused")
fun jobExample() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception $throwable")
    }
    val scope = CoroutineScope(Job() + exHandler)

    // Launch & throw
    scope.launch {
        println("Coroutine 1 starts")
        delay(20)
        println("Coroutine 1 fails")
        throw RuntimeException()
    }

    // Launch & prove CancellationException from Coroutine 1
    scope.launch {
        println("Coroutine 2 starts")
        delay(500)
        println("Coroutine 2 completed (This will not print)")
    }.invokeOnCompletion {
        if (it is CancellationException)
            println("Coroutine 2 got cancelled")
    }

    // Prove launch does not launch when using a cancelled scope
    Thread.sleep(1_000)
    scope.launch {
        println("Coroutine 3 starts (This will not print)")
    }
    Thread.sleep(100)
    println("Scope got cancelled: ${!scope.isActive} (is true)")
}
