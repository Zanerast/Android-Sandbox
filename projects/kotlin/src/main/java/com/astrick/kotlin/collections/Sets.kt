package com.astrick.kotlin.collections

// Sets are unordered collections
// Are faster than lists, but use more memory
// Time it takes to check if a set contains an element does increase with size
// Even faster than arrays since it finds by hash code and not checking from index 0

// Use hash codes as indices
// Holds a list that generally contain 1 item

// Main benefit is ensuring uniqueness

// Are they faster than arrays?
fun main() {
    val rockPlanets = setOf("Mercury", "Venus", "Earth", "Mars", "Duplicate")
    val gasPlanets = setOf("Jupiter", "Saturn", "Uranus", "Duplicate", "Neptune")
    println(rockPlanets.contains("Mercury"))
    
    
    val intersection = rockPlanets.intersect(gasPlanets)
    println(intersection) // [Duplicate]
    val union = rockPlanets.union(gasPlanets)
    println(union) // [Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Duplicate]
}

// References:
// https://developer.android.com/codelabs/basic-android-kotlin-compose-collections?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-3-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-collections#3
