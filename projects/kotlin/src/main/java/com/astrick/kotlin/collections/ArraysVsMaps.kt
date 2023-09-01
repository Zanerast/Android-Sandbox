package com.astrick.kotlin.collections

import com.astrick.kotlin.NUMBER_OF_ITERATIONS
import com.astrick.kotlin.calculateStats
import com.astrick.kotlin.calculateTValue
import com.astrick.kotlin.printResults
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.math.sqrt

private const val NUMBER_OF_ELEMENTS = 1000
private const val MAX_INDEX = NUMBER_OF_ELEMENTS - 1

fun main() {
    
    val array = Array(NUMBER_OF_ELEMENTS) { it } // equivalent to arrayOf()
    val primitiveArray = IntArray(NUMBER_OF_ELEMENTS) { it } // equivalent to intArrayOf()
    
    val map = mutableMapOf<Int, Int>()
    repeat(NUMBER_OF_ELEMENTS) {
        map[it] = it
    }
    
    // Warm-up phase
    repeat(NUMBER_OF_ITERATIONS) {
        val random = Random.nextInt(MAX_INDEX)
        array[random]
        primitiveArray[random]
        map[random]
    }
    
    // Benchmarking phase
    val arrayGetTimes = mutableListOf<Long>()
    val arraySetTimes = mutableListOf<Long>()
    val arrayContainsTimes = mutableListOf<Long>()
    val primitiveArrayGetTimes = mutableListOf<Long>()
    val primitiveArraySetTimes = mutableListOf<Long>()
    val primitiveArrayContainsTimes = mutableListOf<Long>()
    val mapGetTimes = mutableListOf<Long>()
    val mapSetTimes = mutableListOf<Long>()
    val mapContainsTimes = mutableListOf<Long>()
    
    repeat(NUMBER_OF_ITERATIONS) {
        // Get tests
        val randomGet = Random.nextInt(MAX_INDEX)
        val arrayGetTime = measureNanoTime {
            array[randomGet]
        }
        arrayGetTimes.add(arrayGetTime)
    
        val primitiveArrayGetTime = measureNanoTime {
            primitiveArray[randomGet]
        }
        primitiveArrayGetTimes.add(primitiveArrayGetTime)
        
        val mapTimes = measureNanoTime {
            map[randomGet]
        }
        mapGetTimes.add(mapTimes)
    
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
        
        val mapSetTime = measureNanoTime {
            map[randomSet] = randomSet
        }
        mapSetTimes.add(mapSetTime)
    
        // Contains tests
        val randomContains = Random.nextInt(MAX_INDEX)
        val arrayContainsTime = measureNanoTime {
            array.contains(randomContains)
        }
        arrayContainsTimes.add(arrayContainsTime)
    
        val primitiveArrayContainsTime = measureNanoTime {
            primitiveArray.contains(randomContains)
        }
        primitiveArrayContainsTimes.add(primitiveArrayContainsTime)
    
        val mapContainsTime = measureNanoTime {
            map.contains(randomContains)
        }
        mapContainsTimes.add(mapContainsTime)
    }
    
    val tValue = calculateTValue()
    
    // Gets
    val arrayStats = calculateStats(arrayGetTimes)
    val primitiveArrayStats = calculateStats(primitiveArrayGetTimes)
    val mapStats = calculateStats(mapGetTimes)
    
    val arrayConfidenceInterval = tValue * (arrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArrayConfidenceInterval = tValue * (primitiveArrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapConfidenceInterval = tValue * (mapStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    printResults("Array Get Time:", arrayStats, arrayConfidenceInterval)
    printResults("Primitive Array Get Time:", primitiveArrayStats, primitiveArrayConfidenceInterval)
    printResults("Map Get Time:", mapStats, mapConfidenceInterval)
    
    // Sets
    val arraySetStats = calculateStats(arraySetTimes)
    val primitiveArraySetStats = calculateStats(primitiveArraySetTimes)
    val mapSetStats = calculateStats(mapSetTimes)
    
    val arraySetConfidenceInterval = tValue * (arraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArraySetConfidenceInterval = tValue * (primitiveArraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapSetConfidenceInterval = tValue * (mapSetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Array Set Time:", arraySetStats, arraySetConfidenceInterval)
    printResults("Primitive Array Set Time:", primitiveArraySetStats, primitiveArraySetConfidenceInterval)
    printResults("Map Set Time:", mapSetStats, mapSetConfidenceInterval)
    
    // Contains
    val arrayContainsStats = calculateStats(arrayContainsTimes)
    val primitiveArrayContainsStats = calculateStats(primitiveArrayContainsTimes)
    val mapContainsStats = calculateStats(mapContainsTimes)
    
    val arrayContainsConfidenceInterval = tValue * (arrayContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val primitiveArrayContainsConfidenceInterval = tValue * (primitiveArrayContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapContainsConfidenceInterval = tValue * (mapContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Array Contains Time:", arrayContainsStats, arrayContainsConfidenceInterval)
    printResults("Primitive Contains Time:", primitiveArrayContainsStats, primitiveArrayContainsConfidenceInterval)
    printResults("Map Contains Time:", mapContainsStats, mapContainsConfidenceInterval)
}

