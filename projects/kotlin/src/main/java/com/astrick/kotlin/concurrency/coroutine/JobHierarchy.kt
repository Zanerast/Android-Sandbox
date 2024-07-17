package com.astrick.kotlin.concurrency.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun jobHierarchyExample() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    val coroutineJob = scope.launch {
        println("Starting coroutine")
        delay(1_000)
    }

    Thread.sleep(100)

    // True
    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
}
