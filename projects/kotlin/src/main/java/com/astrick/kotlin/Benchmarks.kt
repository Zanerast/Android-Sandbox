package com.astrick.kotlin

import androidx.lifecycle.MutableLiveData
import org.apache.commons.math3.distribution.TDistribution
import org.apache.commons.math3.stat.descriptive.SummaryStatistics

const val CONFIDENCE_LEVEL = 0.95
const val NUMBER_OF_ITERATIONS = 1000

fun calculateStats(times: List<Long>): SummaryStatistics {
    val stats = SummaryStatistics()
    times.forEach { stats.addValue(it.toDouble()) }
    return stats
}

fun calculateTValue(): Double {
    val tDistribution = TDistribution(NUMBER_OF_ITERATIONS - 1.0)
    return tDistribution.inverseCumulativeProbability(1.0 - (1.0 - CONFIDENCE_LEVEL) / 2)
}

fun printResults(label: String, stats: SummaryStatistics, confidenceInterval: Double) {
    val mean = stats.mean
    println("$label:")
    println("%.2f ns (± %.2f ns) [${CONFIDENCE_LEVEL * 100}%% confidence]".format(mean, confidenceInterval))
    println()
}

fun main() {
    val liveData = MutableLiveData<Int>()
    repeat(10_000) {
        liveData.value = liveData.value?.plus(1)
    }

    println("Total: ${liveData.value}")
}
