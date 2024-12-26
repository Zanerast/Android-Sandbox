package com.astrick.sandbox.kotlin.coroutines.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demonstrates exception handling in a coroutine launched from a `launch` block.
 *
 * Normally, exceptions thrown in an `async` coroutine are stored in the `Deferred` object and only triggered when `await()` is called.
 * However, in this example, since the `async` coroutine is launched within a `launch` coroutine (which is fire-and-forget),
 * the exception is thrown immediately when the `async` block is executed, before `await()` is called.
 *
 * @see asyncExceptionExample
 *
 */
@Suppress("unused")
fun asyncNestedExceptionExample() {
    val exHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught $throwable in Coroutine Exception Handler")
    }

    val scope = CoroutineScope(Job() + exHandler)

    scope.launch {
        val deferred = async {
            delay(200)
            throw RuntimeException()
        }
    }

    Thread.sleep(500)
}
