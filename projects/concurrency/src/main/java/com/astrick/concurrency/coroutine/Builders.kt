package com.astrick.concurrency.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun asyncExample() = runBlocking {
    // Async returns a deferred, which is just a Job with Result
    val deferred = async {
        6 * 7
    }
    val result = deferred.await()
    println(result) // 42
}
