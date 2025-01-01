@file:Suppress("unused", "JavaMapForEach", "MayBeConstant", "UNUSED_PARAMETER")

package com.astrick.sandbox.kotlin.coroutines.versions

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/*
    Coroutines became experimental.
    Reference: kotlinlang.org/docs/whatsnew11.html#coroutines-experimental
 */

/*
    Type Aliases
    Reference: kotlinlang.org/docs/whatsnew11.html#type-aliases
 */
typealias OscarWinners = Map<String, String>

/*
    Bound callable references
    Reference: kotlinlang.org/docs/whatsnew11.html#bound-callable-references
 */
val numberRegex = "\\d+".toRegex()
val numbers = listOf("abc", "123", "456").filter(numberRegex::matches)

/*
    Sealed Classes no longer require children to to nested
    Reference: kotlinlang.org/docs/whatsnew11.html#sealed-and-data-classes
 */
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
data object NotANumber : Expr()

/*
    Destructing in lambdas
    Reference: kotlinlang.org/docs/whatsnew11.html#destructuring-in-lambdas
 */
val map = mapOf(1 to "one", 2 to "two")
fun destructing() {
    // before
    map.mapValues { entry ->
        val (key, value) = entry
        "$key -> $value!"
    }
    // now
    map.mapValues { (key, value) ->
        "$key -> $value!"
    }
}

/*
    Underscores for unused parameters
    Reference: kotlinlang.org/docs/whatsnew11.html#underscores-for-unused-parameters
 */
fun underscore() {
    map.forEach { _, value -> println("$value!") }
}

/*
    Underscores in numeric literals to separate groups of digits
    Reference: kotlinlang.org/docs/whatsnew11.html#underscores-in-numeric-literals
 */
val bytes = 0b11010010_01101001_10010100_10010010

/*
    Shorter syntax for properties / type inference
    Reference: kotlinlang.org/docs/whatsnew11.html#shorter-syntax-for-properties
 */
data class Person(val name: String, val age: Int) {
    val isAdult
        get() = age >= 20 // Property type inferred to be 'Boolean'
}

/*
    Inline property accessors if the property doesn't have a backing field
    Reference: kotlinlang.org/docs/whatsnew11.html#inline-property-accessors
 */
val <T> List<T>.lastIndex: Int
    inline get() = this.size - 1

/*
    Local delegated properties.
    You can now use the delegated property syntax with local variables.
    Reference: kotlinlang.org/docs/whatsnew11.html#local-delegated-properties
 */
fun localDelegatedProperty() {
    val answer by lazy {
        println("Calculating the answer...")
        42
    }
    println("The answer is $answer.")   // answer is calculated at this point
}


/*
    Interception of delegated property binding. Useful for if we want
    to check the property name before binding using operator fun provideDelegate
    TODO: Find a real world example use-case
    Reference: kotlinlang.org/docs/whatsnew11.html#interception-of-delegated-property-binding
 */
sealed interface Resource<T> {
    fun load(): T

    data object ImageId : Resource<Int> {
        override fun load(): Int = 123
    }

    data object Text : Resource<String> {
        override fun load(): String = "Sample Text"
    }
}

class ResourceLoader<T>(private val resource: Resource<T>) {
    operator fun provideDelegate(thisRef: MyUI, prop: KProperty<*>): ReadOnlyProperty<MyUI, T> {
        checkProperty(thisRef, prop.name)
        return ReadOnlyProperty { _, _ -> resource.load() }
    }

    private fun checkProperty(thisRef: MyUI, name: String) {
        println("Checking")
    }
}

fun <T> bindResource(resource: Resource<T>): ResourceLoader<T> = ResourceLoader(resource)

class MyUI {
    val image by bindResource(Resource.ImageId)
    val text by bindResource(Resource.Text)
}

/*
    Generic enum value access.
    Enumerate the values of an enum class in a generic way using enumValues.
    Reference: kotlinlang.org/docs/whatsnew11.html#generic-enum-value-access
 */
inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}
