package com.astrick.kotlin.collections

fun main() {
    val rockPlanets = arrayOf("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
    val solarSystem = rockPlanets + gasPlanets
    
    // To add an additional element to an array, either create a new array by adding the old one
    val newSolarSystem = solarSystem + "Pluto"
    println(newSolarSystem.map { it })
}
