@file:Suppress("unused", "UNUSED_VARIABLE")

package com.astrick.sandbox.kotlin.coroutines.versions

/*
    String to number conversions.
    A bunch of new extensions on the String class to convert it to a number
    without throwing an exception on invalid number.
    Reference: kotlinlang.org/docs/whatsnew11.html#string-to-number-conversions
 */
val port = System.getenv("PORT")?.toIntOrNull() ?: 80

/*
    onEach()
    Reference: kotlinlang.org/docs/whatsnew11.html#oneach
 */

/*
    also(), takeIf(), & takeUnless()

    also is like apply: it takes the receiver, does some action on it, and returns that receiver.
    The difference is that in the block inside apply the receiver is available as this,
    while in the block inside also it's available as it (and you can give it another name if you want).
    This comes handy when you do not want to shadow this from the outer scope

    takeIf is like filter for a single value. It checks whether the receiver meets the predicate,
    and returns the receiver, if it does or null if it doesn't.
    Combined with an elvis operator (?:) and early returns it allows writing constructs like below.

    takeUnless is the same as takeIf, but it takes the inverted predicate.
    It returns the receiver when it doesn't meet the predicate and null otherwise.

    Reference: kotlinlang.org/docs/whatsnew11.html#also-takeif-and-takeunless
 */
data class Block(var content: String = "")

fun Block.copy() = Block().also {
    it.content = this.content
}

fun findIndexWithTakeIf(keyword: String, input: String) {
    val index = input.indexOf(keyword).takeIf { it >= 0 } ?: error("keyword not found")
}

fun findIndexWithTakeUnless(keyword: String, input: String) {
    val index = input.indexOf(keyword).takeUnless { it < 0 } ?: error("keyword not found")
}

/*
    groupingBy()
    Can be used to group a collection by key and fold each group simultaneously.
    For example, it can be used to count the number of words starting with each letter.
    Reference: kotlinlang.org/docs/whatsnew11.html#groupingby
 */
fun group(words: List<String>) {
    val frequencies = words.groupingBy { it.first() }.eachCount()
}

/*
    Map.toMap() and Map.toMutableMap()
    Can be used for easy copying of maps.
    Reference: kotlinlang.org/docs/whatsnew11.html#map-tomap-and-map-tomutablemap
 */
class ImmutablePropertyBag(map: Map<String, Any>) {
    private val mapCopy = map.toMap()
}

/*
    Map.minus(key)
    There are 4 overloads available:
    for removing a single key, a collection of keys, a sequence of keys and an array of keys.
    Reference: kotlinlang.org/docs/whatsnew11.html#map-minus-key
 */
fun mapMinus() {
    val map = mapOf("key" to 42)
    val emptyMap = map - "key"
}

/*
    minOf() and maxOf()
    Reference: kotlinlang.org/docs/whatsnew11.html#minof-and-maxof
 */
val list1 = listOf("a", "b")
val list2 = listOf("x", "y", "z")
val minSize = minOf(list1.size, list2.size)
val longestList = maxOf(list1, list2, compareBy { it.size })

/*
    Array-like List instantiation functions
    Reference: kotlinlang.org/docs/whatsnew11.html#array-like-list-instantiation-functions
 */
val squares = List(10) { index -> index * index }
val mutable = MutableList(10) { 0 }

/*
    Map.getValue()
    Reference: kotlinlang.org/docs/whatsnew11.html#map-getvalue
 */
fun getValueFromMap() {
    val map = mapOf("key" to 42)
    // returns non-nullable Int value 42
    val value: Int = map.getValue("key")
    // map.getValue("anotherKey") // <- this will throw NoSuchElementException

    val mapWithDefault = map.withDefault { k -> k.length }
    // returns 4
    val value2 = mapWithDefault.getValue("key2")
}

/*
    Abstract collections.
    These abstract classes can be used as base classes when implementing Kotlin collection classes.
    For implementing read-only collections there are AbstractCollection, AbstractList, AbstractSet and AbstractMap,
    and for mutable collections there are AbstractMutableCollection, AbstractMutableList, AbstractMutableSet and AbstractMutableMap.
    On JVM, these abstract mutable collections inherit most of their functionality from JDK's abstract collections.
    Reference: kotlinlang.org/docs/whatsnew11.html#abstract-collections
 */

/*
    Array manipulation functions.
    Element-by-element operations on arrays:
    - comparison (contentEquals and contentDeepEquals),
    - hash code calculation (contentHashCode and contentDeepHashCode),
    - and conversion to a string (contentToString and contentDeepToString).
    They're supported both for the JVM (where they act as aliases for the corresponding functions in java.util.Arrays)
    and for JS (where the implementation is provided in the Kotlin standard library)
    Reference: kotlinlang.org/docs/whatsnew11.html#array-manipulation-functions
 */
fun main() {
    arrayManipulation()
}
fun arrayManipulation() {
    val array = arrayOf("a", "b", "c")
    println(array.toString())  // JVM implementation: type-and-hash gibberish
    println(array.contentToString())  // nicely formatted as list
    println(array.contentDeepToString())  // nicely formatted as list
}
