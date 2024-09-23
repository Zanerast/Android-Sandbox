package com.astrick.kotlin.concurrency.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    // flowOf
    val firstFlow = flowOf(1,2,3)
    firstFlow.collect { emitted ->
        println("firstFlow: $emitted")
    }

    // asFlow
    listOf("A","B","C").asFlow().collect { emitted ->
        println("secondFlow: $emitted")
    }

    // flow
//    flow {
//        delay(2_000)
//        emit("item emitted after 2000ms")
//        emitAll(firstFlow)
//    }.collect { emitted ->
//        println("flow{}: $emitted")
//    }

    // flow
    val flowA = flow {
        delay(500)
        emit("flowA item emitted after 500ms")
        delay(500)
        emit("flowA item emitted after 1_000ms")
        delay(500)
        emit("flowA item emitted after 1_500ms")
    }
    val flowB = flow {
        delay(500)
        emit("flowB item emitted after 500ms")
        delay(500)
        emit("flowB item emitted after 1_000ms")
        delay(500)
        emit("flowB item emitted after 1_500ms")
    }
    flow {
        // Runs sequentially
        emitAll(flowA)
        emitAll(flowB)
    }.collect { emitted ->
        println("flow{}: $emitted")
    }
}