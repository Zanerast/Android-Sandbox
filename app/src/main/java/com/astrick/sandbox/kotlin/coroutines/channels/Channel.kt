package com.astrick.sandbox.kotlin.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demonstrates the use of a `Channel` for communication between coroutines.
 *
 * When to use channels over State/Shared flow:
 * - When the order of events matters and events should not be overwritten (e.g., queue-like behavior)
 * - TODO Find an actual production use-case for channels
 *
 * References:
 * [Intro to Coroutines and Channels](www.youtube.com/watch?v=HpWQUoVURWQ&ab_channel=JetBrains)
 */
fun channelExample() = runBlocking {
    val channel = Channel<String>()

    // Producer coroutines
    launch {
        channel.send("A1")
        channel.send("A2")
        println("A done")
    }
    launch {
        channel.send("B1")
        channel.send("B2")
        println("B done")
    }

    // Consumer coroutine
    launch {
        repeat(4) {
            val x = channel.receive()
            println("Received: $x")
        }
    }

    /*
      Will print:
      A1
      B1
      A done
      B done
      A2
     */

}

private fun main() {
    println("Channel Example")
    channelExample()
    println("--------------")
    println("StateFlow Example")
    stateFlowExample()
}

/*
 * Here to demonstrate the use of `StateFlow` vs 'Channel'.
 *
 * Differences between `StateFlow` and `Channel`:
 * 1. **State Retention**:
 *    - `StateFlow`: Always holds the latest value. Late collectors receive the most recent value only.
 *    - `Channel`: Can buffer or queue multiple values, ensuring all sent values are processed.
 *
 * 2. **Use Case**:
 *    - `StateFlow`: Best for representing a state that changes over time.
 *    - `Channel`: Suitable for event-driven communication where every event is important.
 *
 * 3. **Concurrency**:
 *    - `StateFlow`: Thread-safe and supports atomic updates using `update`.
 *    - `Channel`: Requires careful management of lifecycle and cancellation to avoid resource leaks.
 *
 * 4. **Collection**:
 *    - `StateFlow`: Always active; values are retained until collected.
 *    - `Channel`: Can suspend senders if there's no active collector.
 *
 */
private fun stateFlowExample() = runBlocking {
    val stateFlow = MutableStateFlow("Initial Stateflow")

    // Producer coroutines
    launch {
        stateFlow.update { "C1" }
        stateFlow.update { "C2" }
        println("C done")
    }
    launch {
        stateFlow.update { "D1" }
        stateFlow.update { "D2" }
        println("D done")
    }

    // Collector coroutine
    launch {
        stateFlow.collect { value ->
            println("Received: $value")
        }
    }

    /*
       Will print:
       C done
       D done
       Received: D2
     */

}
