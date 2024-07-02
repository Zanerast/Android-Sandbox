package com.astrick.concurrency.coroutine.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main() {
    val exHandlerOne = CoroutineExceptionHandler { _, throwable ->
        println("Caught $throwable in Coroutine Exception Handler")
    }
    val childExHandler = CoroutineExceptionHandler { _, throwable ->
        println("Should NOT catch $throwable in Coroutine Exception Handler")
    }

    val scope = CoroutineScope(Job())
    scope.launch(exHandlerOne) {
        // exHandlerTwo will not catch, as CoroutineExceptionHandler need to be installed
        // in a top level Coroutine
        launch(childExHandler) {
            throw RuntimeException()
        }
    }
    Thread.sleep(100)
}
