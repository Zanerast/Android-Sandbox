package com.astrick.kotlin.collections

import com.astrick.kotlin.NUMBER_OF_ITERATIONS
import com.astrick.kotlin.calculateStats
import com.astrick.kotlin.calculateTValue
import com.astrick.kotlin.printResults
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.math.sqrt

private const val NUMBER_OF_ELEMENTS = 10000
private const val MAX_INDEX = NUMBER_OF_ELEMENTS - 1

fun main() {
    
    val set = mutableSetOf<Int>()
    
    val map = mutableMapOf<Int, Int>()
    repeat(NUMBER_OF_ELEMENTS) {
        map[it] = it
        set.add(it)
    }
    
    // Warm-up phase
    repeat(NUMBER_OF_ITERATIONS) {
        val random = Random.nextInt(MAX_INDEX)
        set.contains(it)
        map[random]
    }
    
    // Benchmarking phase
    val setAddTimes = mutableListOf<Long>()
    val setContainsTimes = mutableListOf<Long>()
    val mapSetTimes = mutableListOf<Long>()
    val mapContainsTimes = mutableListOf<Long>()
    
    repeat(NUMBER_OF_ITERATIONS) {
        // Set tests
        val randomSet = Random.nextInt(MAX_INDEX)
        val setAddTime = measureNanoTime {
            set.add(randomSet)
        }
        setAddTimes.add(setAddTime)
        
        val mapSetTime = measureNanoTime {
            map[randomSet] = randomSet
        }
        mapSetTimes.add(mapSetTime)
    
        // Contains tests
        val randomContains = Random.nextInt(MAX_INDEX)
        val setContainsTime = measureNanoTime {
            set.contains(randomContains)
        }
        setContainsTimes.add(setContainsTime)
    
        val mapContainsTime = measureNanoTime {
            map.contains(randomContains)
        }
        mapContainsTimes.add(mapContainsTime)
    }
    
    val tValue = calculateTValue()
    
    // Sets
    val setAddStats = calculateStats(setAddTimes)
    val mapSetStats = calculateStats(mapSetTimes)
    
    val setAddConfidenceInterval = tValue * (setAddStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapSetConfidenceInterval = tValue * (mapSetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Set Add Time:", setAddStats, setAddConfidenceInterval)
    printResults("Map Set Time:", mapSetStats, mapSetConfidenceInterval)
    
    // Contains
    val setContainsStats = calculateStats(setContainsTimes)
    val mapContainsStats = calculateStats(mapContainsTimes)
    
    val arrayContainsConfidenceInterval = tValue * (setContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapContainsConfidenceInterval = tValue * (mapContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("Set Contains Time:", setContainsStats, arrayContainsConfidenceInterval)
    printResults("Map Contains Time:", mapContainsStats, mapContainsConfidenceInterval)
}
