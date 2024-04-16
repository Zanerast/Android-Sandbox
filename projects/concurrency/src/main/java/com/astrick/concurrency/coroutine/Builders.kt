package com.astrick.concurrency.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    asyncExample()
}

fun asyncExample() = runBlocking {
    val deferred = async {
        6 * 7
    }
    val result = deferred.await()
    println(result) // 42
}
