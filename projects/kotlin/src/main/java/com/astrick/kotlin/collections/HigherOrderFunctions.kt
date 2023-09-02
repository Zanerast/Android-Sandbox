package com.astrick.kotlin.collections


fun main() {
    // Fold
    // Basically an accumulator
    // There is also reduce, which starts with first value in collection. This won't work with cookies, since cookie is not a number
    println("-------------------")
    val totalPrice = cookies.fold(0.0) { acc, cookie ->
        acc + cookie.price
    }
    println("FOLD: Total price: $$totalPrice")
    val sum = cookies.sumOf { it.price }
    println("SUM: Total price: $$sum")
    
    // Group-by
    // Creates a map
    println("-------------------")
    val groupedMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()
    println("GROUP-BY: Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("GROUP-BY: Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    
    // Partition
    println("-------------------")
    val partitioned = cookies.partition { it.softBaked }
    val softBakedPartitioned = partitioned.first
    val crunchyPartitioned = partitioned.second
    println("PARTITIONED Soft cookies:")
    softBakedPartitioned.forEach {
        println("${it.name} - $${it.price}")
    }
    println("PARTITIONED Crunchy cookies:")
    crunchyPartitioned.forEach {
        println("${it.name} - $${it.price}")
    }
}

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)
