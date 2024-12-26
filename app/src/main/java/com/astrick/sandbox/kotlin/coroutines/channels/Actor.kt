package com.astrick.sandbox.kotlin.coroutines.channels

import com.astrick.sandbox.kotlin.coroutines.channels.CounterMsg.GetCounter
import com.astrick.sandbox.kotlin.coroutines.channels.CounterMsg.IncCounter
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

/**
 * **Deprecated**: Currently no replacements for actors.
 *
 * Demonstrates the usage of an actor in Kotlin coroutines.
 *
 * References:
 * - [Android-World article](https://medium.com/p/e25f701fa213)
 * - [Coroutines Issue 87](https://github.com/Kotlin/kotlinx.coroutines/issues/87)
 * - [KotlinConf 2017 ](https://youtu.be/YrrUCSi72E8?t=2626)
 * - [Official Docs](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/actor.html)
 */
@Suppress("unused")
@OptIn(ObsoleteCoroutinesApi::class)
fun channelActorExample() = runBlocking {
    val counterActor = actor<CounterMsg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is IncCounter -> counter++
                is GetCounter -> msg.response.complete(counter)
            }
        }
    }

    counterActor.send(IncCounter)
    val response = CompletableDeferred<Int>()
    counterActor.send(GetCounter(response = response))
    println("Counter: ${response.await()}")  // Output will be "Counter: 1"
}

private sealed interface CounterMsg {
    object IncCounter : CounterMsg
    class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg
}
