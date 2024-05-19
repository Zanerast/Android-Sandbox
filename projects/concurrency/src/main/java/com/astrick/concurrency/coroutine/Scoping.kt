package com.astrick.concurrency.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun coroutineScopeExample() {
    val scope = CoroutineScope(Job())
    scope.launch {
        coroutineScope {
            launch {
                println("Starting task 1")
                delay(100)
                println("Task 1 completed")
            }
            launch {
                println("Starting task 2")
                delay(100)
                println("Task 2 completed")
            }
        }
        // Task 3 will wait for coroutineScope to finish before starting
        launch {
            println("Starting task 3")
            delay(100)
            println("Task 3 completed")
        }
    }
    Thread.sleep(1_000)
}

fun supervisorScopeExample() {
    val scope = CoroutineScope(Job())
    scope.launch {
        supervisorScope {
            launch {
                println("Starting task 1")
                delay(100)
                println("Task 1 completed")
            }
            launch {
                println("Starting task 2")
                delay(100)
                println("Task 2 completed")
            }
        }
        // Task 3 will wait for supervisorScope to finish before starting
        launch {
            println("Starting task 3")
            delay(100)
            println("Task 3 completed")
        }
    }
    Thread.sleep(1_000)
}
