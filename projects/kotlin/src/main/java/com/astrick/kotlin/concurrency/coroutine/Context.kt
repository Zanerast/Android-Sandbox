package com.astrick.kotlin.concurrency.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

// Dispatchers.Main
// Android Main dispatcher uses Handler for the main looper internally

// Dispatchers.IO
// Uses shared thread pool internally, limited to 64 threads

// Dispatchers.Default
// Heavy computations
// Uses shared thread pool internally, max equal to number of CPU cores

suspend fun coroutineContextExample() {
    // Coroutine context elements consist of Dispatcher, Job, Error Handler, Name
    val customContext = Dispatchers.Main

    val job = CoroutineScope(customContext + CoroutineName("Main example coroutine")).launch {
        println("Coroutine is running on thread: ${Thread.currentThread().name}")

        calculateFactorialOf()

        println("Coroutine is still running on thread: ${Thread.currentThread().name}")
    }

    job.join()
}

private suspend fun calculateFactorialOf(number: Int = 10_000): BigInteger {
    return withContext(Dispatchers.Default + CoroutineName("Factorial computation coroutine")) {
        var factorial = BigInteger.ONE
        for (i in 1..number) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        factorial
    }
}

