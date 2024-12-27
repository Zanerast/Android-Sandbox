package com.astrick.sandbox.kotlin.coroutines.context

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demonstrates coroutine hierarchy and parent-child relationships in coroutines.
 *
 * This function creates a parent `Job` and a `CoroutineScope` using it. A coroutine is launched
 * in this scope, making its `Job` a child of the parent `Job`. The relationship is verified
 * using the `children` property of the parent `Job`.
 */
@Suppress("unused")
fun hierarchyExample() {
    val job = Job()
    val scope = CoroutineScope(job)

    val coroutineJob = scope.launch {
        println("Starting coroutine")
        delay(1_000)
    }

    Thread.sleep(100)

    // prints: True
    println("Is coroutineJob a child of scopeJob? => ${job.children.contains(coroutineJob)}")
}
