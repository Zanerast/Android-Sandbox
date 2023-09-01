package com.astrick.kotlin.collections

import com.astrick.kotlin.calculateStats
import com.astrick.kotlin.calculateTValue
import com.astrick.kotlin.printResults
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.math.sqrt

private const val NUMBER_OF_ITERATIONS = 1000
private const val NUMBER_OF_ELEMENTS = 10
private const val MAX_INDEX = NUMBER_OF_ELEMENTS - 1

fun main() {
    
    val array = Array(NUMBER_OF_ELEMENTS) { it } // equivalent to arrayOf()
    val primitiveArray = IntArray(NUMBER_OF_ELEMENTS) { it } // equivalent to intArrayOf()
    
    val set = mutableSetOf(NUMBER_OF_ELEMENTS)
    repeat(NUMBER_OF_ELEMENTS) {
        set.add(it)
    }
    
    // Warm-up phase
    repeat(NUMBER_OF_ITERATIONS) {
        val random = Random.nextInt(MAX_INDEX)
        array[random]
        primitiveArray[random]
        set.contains(random)
    }
    
    // Benchmarking phase
    val arrayContainsTimes = mutableListOf<Long>()
    val arraySetTimes = mutableListOf<Long>()
    val primitiveArrayContainsTimes = mutableListOf<Long>()
    val primitiveArraySetTimes = mutableListOf<Long>()
    val setContainsTimes = mutableListOf<Long>()
    val setSetTimes = mutableListOf<Long>()
    
    repeat(NUMBER_OF_ITERATIONS) {
        // Get tests
        val randomGet = Random.nextInt(MAX_INDEX)
        val arrayContainsTime = measureNanoTime {
            array.contains(randomGet)
        }
        arrayContainsTimes.add(arrayContainsTime)
    
        val primitiveArrayContainsTime = measureNanoTime {
            primitiveArray.contains(randomGet)
        }
        primitiveArrayContainsTimes.add(primitiveArrayContainsTime)
        
        val setTime = measureNanoTime {
            set.contains(randomGet)
        }
        setContainsTimes.add(setTime)
    
        // Set tests
        val randomSet = Random.nextInt(MAX_INDEX)
        val arraySetTime = measureNanoTime {
            array[randomSet] = randomSet
        }
        arraySetTimes.add(arraySetTime)
        val primitiveArraySetTime = measureNanoTime {
            primitiveArray[randomSet] = randomSet
        }
        primitiveArraySetTimes.add(primitiveArraySetTime)
        
        val setAddTime = measureNanoTime {
            set.add(randomSet)
        }
        setSetTimes.add(setAddTime)
    }
    
    val tValue = calculateTValue()
    
    // Gets
    val arrayStats = calculateStats(arrayContainsTimes)
    val primitiveArrayStats = calculateStats(primitiveArrayContainsTimes)
    val listStats = calculateStats(setContainsTimes)
    
    val arrayConfidenceInterval = tValue * (arrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArrayConfidenceInterval = tValue * (primitiveArrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val listConfidenceInterval = tValue * (listStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    printResults("Array contains Time:", arrayStats, arrayConfidenceInterval)
    printResults("Primitive Array contains Time:", primitiveArrayStats, primitiveArrayConfidenceInterval)
    printResults("Set contains Time:", listStats, listConfidenceInterval)
    
    // Sets
    val arraySetStats = calculateStats(arraySetTimes)
    val primitiveArraySetStats = calculateStats(primitiveArraySetTimes)
    val listSetStats = calculateStats(setSetTimes)
    
    val arraySetConfidenceInterval = tValue * (arraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArraySetConfidenceInterval = tValue * (primitiveArraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val listSetConfidenceInterval = tValue * (listSetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Array Set Time:", arraySetStats, arraySetConfidenceInterval)
    printResults("Primitive Array Set Time:", primitiveArraySetStats, primitiveArraySetConfidenceInterval)
    printResults("Set Add Time:", listSetStats, listSetConfidenceInterval)
}
