package com.astrick.concurrency.coroutine.exceptions

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
    val childExHandler = CoroutineExceptionHandler { _, throwable ->
        println("Should NOT catch as this is the child")
    }

    val scope = CoroutineScope(Job() + exHandler)
    // Exception is stored in the deferred object
    val deferred = scope.async(childExHandler) {
        delay(200)
        throw RuntimeException()
    }

    scope.launch {
        // Will now throw the stored exception
        deferred.await()
    }

    Thread.sleep(500)
}
