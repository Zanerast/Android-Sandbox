package com.astrick.sandbox.kotlin.coroutines.context

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Demonstrates the use of a `SupervisorJob` to manage exceptions in child coroutines independently.
 *
 * A `SupervisorJob` ensures that a failure in one child coroutine does not cancel the entire scope or other coroutines.
 * This behavior differs from a regular `Job`, where a failure in one child would propagate and cancel the whole scope.
 *
 * This example shows:
 * - How a failing child coroutine does not affect the parent scope or other coroutines within the same scope.
 *
 * Key observations:
 * - A failure in one coroutine does not cancel the parent scope, and sibling coroutines can still execute.
 * - The parent scope remains active, even if a child coroutine fails.
 */
@Suppress("unused")
fun supervisorJobExample() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception $throwable")
    }
    val scope = CoroutineScope(SupervisorJob() + exHandler)
    scope.launch {
        println("Coroutine 1 starts")
        delay(20)
        println("Coroutine 1 fails")
        throw RuntimeException()
    }
    scope.launch {
        println("Coroutine 2 starts")
        delay(500)
        println("Coroutine 2 completed")
    }
    Thread.sleep(1_000)

    /*
      Remember: The scope remains active until it is explicitly cancelled or its job completes.
      The isActive property indicates whether the scope is still capable of launching new coroutines,
      not necessarily whether there are coroutines actively running within it.
     */
    println("Scope got cancelled: ${!scope.isActive} (is false)")
}
