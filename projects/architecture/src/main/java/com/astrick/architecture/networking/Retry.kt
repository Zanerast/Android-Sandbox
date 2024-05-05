package com.astrick.architecture.networking

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import timber.log.Timber

suspend fun <T> retryWithTimeout(
    numberOfRetries: Int,
    timeoutMillis: Long,
    block: suspend () -> T
): T {
    return retry(numberOfRetries) {
        withTimeout(timeoutMillis) {
            block()
        }
    }
}

suspend fun <T> retry(
    numberOfRetries: Int,
    initialDelayMillis: Long = 100,
    maxDelayMillis: Long = 1000,
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelayMillis
    repeat(numberOfRetries) {
        try {
            return block()
        } catch (e: Exception) {
            Timber.e(e)
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
    }

    return block()
}
