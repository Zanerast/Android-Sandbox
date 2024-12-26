package com.astrick.sandbox.kotlin.coroutines.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Demonstrates handling exceptions in an `async` coroutine.
 *
 * This example shows how exceptions thrown inside an `async` coroutine are captured and rethrown when `await()` is called.
 */
fun asyncExceptionExample() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught $throwable in Coroutine Exception Handler")
    }
    val scope = CoroutineScope(Job() + exHandler)

    val deferred = scope.async {
        // RuntimeException will be stored in the deferred object
        throw RuntimeException()
    }

    scope.launch {
        // await() will now cause the stored exception to be thrown
        deferred.await()
    }

    Thread.sleep(500)
}
