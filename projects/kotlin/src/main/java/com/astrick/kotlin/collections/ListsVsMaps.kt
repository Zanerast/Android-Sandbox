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
    
    val list = MutableList(NUMBER_OF_ELEMENTS) { it } // equivalent to arrayOf()
    
    val map = mutableMapOf<Int, Int>()
    repeat(NUMBER_OF_ELEMENTS) {
        map[it] = it
    }
    
    // Warm-up phase
    repeat(NUMBER_OF_ITERATIONS) {
        val random = Random.nextInt(MAX_INDEX)
        list[random]
        map[random]
    }
    
    // Benchmarking phase
    val listGetTimes = mutableListOf<Long>()
    val listSetTimes = mutableListOf<Long>()
    val listContainsTimes = mutableListOf<Long>()
    val mapGetTimes = mutableListOf<Long>()
    val mapSetTimes = mutableListOf<Long>()
    val mapContainsTimes = mutableListOf<Long>()
    
    repeat(NUMBER_OF_ITERATIONS) {
        // Get tests
        val randomGet = Random.nextInt(MAX_INDEX)
        val listGetTime = measureNanoTime {
            list[randomGet]
        }
        listGetTimes.add(listGetTime)
        
        val mapTimes = measureNanoTime {
            map[randomGet]
        }
        mapGetTimes.add(mapTimes)
    
        // Set tests
        val randomSet = Random.nextInt(MAX_INDEX)
        val listSetTime = measureNanoTime {
            list[randomSet] = randomSet
        }
        listSetTimes.add(listSetTime)
        
        val mapSetTime = measureNanoTime {
            map[randomSet] = randomSet
        }
        mapSetTimes.add(mapSetTime)
    
        // Contains tests
        val randomContains = Random.nextInt(MAX_INDEX)
        val listContainsTime = measureNanoTime {
            list.contains(randomContains)
        }
        listContainsTimes.add(listContainsTime)
    
        val mapContainsTime = measureNanoTime {
            map.contains(randomContains)
        }
        mapContainsTimes.add(mapContainsTime)
    }
    
    val tValue = calculateTValue()
    
    // Gets
    val arrayStats = calculateStats(listGetTimes)
    val mapStats = calculateStats(mapGetTimes)
    
    val arrayConfidenceInterval = tValue * (arrayStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapConfidenceInterval = tValue * (mapStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    printResults("List Get Time:", arrayStats, arrayConfidenceInterval)
    printResults("Map Get Time:", mapStats, mapConfidenceInterval)
    
    // Sets
    val arraySetStats = calculateStats(listSetTimes)
    val mapSetStats = calculateStats(mapSetTimes)
    
    val arraySetConfidenceInterval = tValue * (arraySetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapSetConfidenceInterval = tValue * (mapSetStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("List Set Time:", arraySetStats, arraySetConfidenceInterval)
    printResults("Map Set Time:", mapSetStats, mapSetConfidenceInterval)
    
    // Contains
    val arrayContainsStats = calculateStats(listContainsTimes)
    val mapContainsStats = calculateStats(mapContainsTimes)
    
    val arrayContainsConfidenceInterval = tValue * (arrayContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    val mapContainsConfidenceInterval = tValue * (mapContainsStats.standardDeviation / sqrt(NUMBER_OF_ITERATIONS.toDouble()))
    
    println("-----------------------------------")
    printResults("List Contains Time:", arrayContainsStats, arrayContainsConfidenceInterval)
    printResults("Map Contains Time:", mapContainsStats, mapContainsConfidenceInterval)
}
