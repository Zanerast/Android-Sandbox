package com.astrick.concurrency.thread

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

private const val REPEAT_TIMES = 1000

fun main() {
    threadExample()
}

// Time taken to get to Main ends: 117ms
// Note: routines are printed sporadically
private fun threadExample() {
    println("Main starts")
    measureTimeMillis {
        repeat(REPEAT_TIMES) {
            threadRoutine(it, 10)
        }
    }.also {
        println("Time taken: $it ms")
    }
    println("Main ends")
}

// Time taken to get to Main ends: 44ms
// Note: routines are printed in correct order
// There coroutines block the main thread for less time & are safer
fun coroutineExample() = runBlocking {
    println("Main starts")
    measureTimeMillis {
        repeat(REPEAT_TIMES) {
            launch {
                coRoutine(it, 10)
            }
        }
    }.also {
        println("Time taken: $it ms")
    }
    println("Main ends")
}

fun threadRoutine(number: Int, delay: Long) {
    thread {
        println("Routine $number starts")
        Thread.sleep(delay)
    }
}

suspend fun coRoutine(number: Int, delay: Long) {
    println("Routine $number starts")
    delay(delay)
}
