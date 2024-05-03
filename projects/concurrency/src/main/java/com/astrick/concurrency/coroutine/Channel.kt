package com.astrick.concurrency.coroutine

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun channelExample() = runBlocking {
    // https://www.youtube.com/watch?v=HpWQUoVURWQ&ab_channel=JetBrains
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        println("A done")
    }
    launch {
        channel.send("B1")
        println("B done")
    }
    launch {
        repeat(3) {
            val x = channel.receive()
            println(x)
        }
    }
    
    // Will print:
    // A1
    // B1
    // A done
    // B done
    // A2
}

fun anotherChannelExample() = runBlocking {
    // https://www.youtube.com/watch?v=YrrUCSi72E8&t=2626s&ab_channel=JetBrains
    val channel = Channel<Int>()
    launch(coroutineContext) {
        repeat(10) { i->
            delay(100)
            channel.send(i)
        }
        channel.close()
    }
    launch(coroutineContext) {
        for (i in channel)
            println(i)
    }
}

// actor == named coroutine & inbox channel
@OptIn(ObsoleteCoroutinesApi::class)
fun actorExample() = runBlocking {
    // https://youtu.be/YrrUCSi72E8?t=2626
    // https://kotlinlang.org/docs/coroutines-guide.html#additional-references
    val printer = actor<Int>(coroutineContext) {
        for (i in channel) {
            println(i)
        }
    }
    launch(coroutineContext) {
        repeat(10) { i ->
            delay(100)
            printer.send(i)
        }
        printer.close()
    }
}
