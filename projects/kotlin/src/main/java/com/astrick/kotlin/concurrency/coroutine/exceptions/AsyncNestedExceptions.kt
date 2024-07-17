package com.astrick.kotlin.concurrency.coroutine.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught $throwable in Coroutine Exception Handler")
    }

    val scope = CoroutineScope(Job() + exHandler)
    // Exception is normally stored in the deferred object and not triggered
    // However since the top-level coroutine is a launch, the async now throws the exception.
    // This is because launch is a fire-and-forget coroutine builder.
    // When an exception is thrown inside its child coroutine (async),
    // it propagates up to the parent coroutine (launch) immediately,
    // and the CoroutineExceptionHandler catches it.
    scope.launch {
        val deferred = async {
            delay(200)
            throw RuntimeException()
        }
    }

    Thread.sleep(500)
}
