package com.astrick.kotlin.collections

import org.apache.commons.math3.distribution.TDistribution
import kotlin.random.Random
import kotlin.system.measureNanoTime
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import kotlin.math.sqrt

private const val CONFIDENCE_LEVEL = 0.95
private const val NUMBER_OF_ITERATIONS = 1000
private const val NUMBER_OF_ELEMENTS = 10
private const val MAX_INDEX = NUMBER_OF_ELEMENTS - 1

fun main() {
    
    val rockPlanetsArray = Array(NUMBER_OF_ELEMENTS) { it }
    val rockPlanetsPrimitiveArray = IntArray(NUMBER_OF_ELEMENTS) { it }
    
    // MutableList and mutableListOf both use ArrayList under the hood (which is a MutableList & Random)
    val rockPlanetsList = MutableList(NUMBER_OF_ELEMENTS) { it }
//    val rockPlanetsList = mutableListOf<Int>()
//    repeat(NUMBER_OF_ELEMENTS) {
//        rockPlanetsList.add(it)
//    }
    
    // Warm-up phase
    repeat(NUMBER_OF_ITERATIONS) {
        val random = Random.nextInt(MAX_INDEX)
        rockPlanetsArray[random]
        rockPlanetsPrimitiveArray[random]
        rockPlanetsList[random]
    }
    
    // Benchmarking phase
    val arrayGetTimes = mutableListOf<Long>()
    val arraySetTimes = mutableListOf<Long>()
    val primitiveArrayGetTimes = mutableListOf<Long>()
    val primitiveArraySetTimes = mutableListOf<Long>()
    val listGetTimes = mutableListOf<Long>()
    val listSetTimes = mutableListOf<Long>()
    
    repeat(NUMBER_OF_ITERATIONS) {
        // Get tests
        val randomGet = Random.nextInt(MAX_INDEX)
        val arrayGetTime = measureNanoTime {
            rockPlanetsArray[randomGet]
        }
        arrayGetTimes.add(arrayGetTime)
    
        val primitiveArrayGetTime = measureNanoTime {
            rockPlanetsPrimitiveArray[randomGet]
        }
        primitiveArrayGetTimes.add(primitiveArrayGetTime)
        
        val listTime = measureNanoTime {
            rockPlanetsList[randomGet]
        }
        listGetTimes.add(listTime)
    
        // Set tests
        val randomSet = Random.nextInt(MAX_INDEX)
        val arraySetTime = measureNanoTime {
            rockPlanetsArray[randomSet] = randomSet
        }
        arraySetTimes.add(arraySetTime)
        val primitiveArraySetTime = measureNanoTime {
            rockPlanetsPrimitiveArray[randomSet] = randomSet
        }
        primitiveArraySetTimes.add(primitiveArraySetTime)
        
        val listSetTime = measureNanoTime {
            rockPlanetsList[randomSet] = randomSet
        }
        listSetTimes.add(listSetTime)
    }
    
    val tValue = calculateTValue()
    
    // Gets
    val arrayStats = calculateStats(arrayGetTimes)
    val primitiveArrayStats = calculateStats(primitiveArrayGetTimes)
    val listStats = calculateStats(listGetTimes)
    
    val arrayConfidenceInterval = tValue * (arrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArrayConfidenceInterval = tValue * (primitiveArrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val listConfidenceInterval = tValue * (listStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    printResults("Array Get Time:", arrayStats, arrayConfidenceInterval)
    printResults("Primitive Array Get Time:", primitiveArrayStats, primitiveArrayConfidenceInterval)
    printResults("List Get Time:", listStats, listConfidenceInterval)
    
    // Sets
    val arraySetStats = calculateStats(arraySetTimes)
    val primitiveArraySetStats = calculateStats(primitiveArraySetTimes)
    val listSetStats = calculateStats(listSetTimes)
    
    val arraySetConfidenceInterval = tValue * (arraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArraySetConfidenceInterval = tValue * (primitiveArraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val listSetConfidenceInterval = tValue * (listSetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Array Set Time:", arraySetStats, arraySetConfidenceInterval)
    printResults("Primitive Array Set Time:", primitiveArraySetStats, primitiveArraySetConfidenceInterval)
    printResults("List Set Time:", listSetStats, listSetConfidenceInterval)
}


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
