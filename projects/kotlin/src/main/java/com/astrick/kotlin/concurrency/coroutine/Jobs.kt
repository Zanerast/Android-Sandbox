package com.astrick.kotlin.concurrency.coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * For Job(): When a child fails it also cancels its siblings
 */
fun jobsExample() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception $throwable")
    }
    val scope = CoroutineScope(Job() + exHandler)
    scope.launch {
        println("Coroutine 1 starts")
        delay(20)
        println("Coroutine 1 fails")
        throw RuntimeException()
    }
    scope.launch {
        println("Coroutine 2 starts")
        delay(500)
        println("Coroutine 2 completed (This will not print)")
    }.invokeOnCompletion {
        if (it is CancellationException)
            println("Coroutine 2 got cancelled")
    }

    Thread.sleep(1_000)
    scope.launch {
        println("Coroutine 3 starts (This will not print)")
    }
    Thread.sleep(100)
    println("Scope got cancelled: ${!scope.isActive} (is true)")
}

/**
 * For Job(): When a child fails it also cancels its siblings
 */
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
        println("Coroutine 2 completed ")
    }.invokeOnCompletion {
        if (it is CancellationException)
            println("Coroutine 2 got cancelled (This will not print)")
    }

    Thread.sleep(1_000)

    // The scope remains active until it is explicitly cancelled or its job completes.
    // The isActive property of the scope indicates whether the scope is still capable of launching
    // new coroutines, not necessarily whether there are coroutines actively running within it.
    println("Scope got cancelled: ${!scope.isActive} (is false)")
}
