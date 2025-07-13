@file:Suppress("UNREACHABLE_CODE")

package com.astrick.sandbox.kotlin.generics

open class Animal {
    fun feed() { /* */ }
}

class Cat: Animal() {
    fun cleanLitter() { /* */ }
}

/*
 * Non-covariance example
 */
class Herd<T: Animal> {
    val size: Int get() = TODO("Provide the return value")
    operator fun get(i: Int): T {
        return TODO("Provide the return value")
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0..cats.size) {
        cats[i].cleanLitter()
    }
    /*
     * ERROR: inferred type is Herd<Cat> but Herd<Animal> was expected.
     * ELI5: Even though cats are animals, a group of cats is not the same as a group of animals.
     * You can't use a group of cats where a group of animals is needed.
     */
//    feedAll(cats)
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0..animals.size) {
        animals[i].feed()
    }
}

/*
 * Covariance example
 * ELI5: By adding 'out', we say that HerdCovariant is a group that can safely be used as a group of animals,
 * even if it's really a group of cats. This is because we only take animals out(read), not put them in(write).
 */
class HerdCovariant<out T: Animal> {
    val size: Int get() = TODO("Provide the return value")
    operator fun get(i: Int): T {
        return TODO("Provide the return value")
    }
}

fun takeCareOfCatsCovariant(cats: HerdCovariant<Cat>) {
    for (i in 0..cats.size) {
        cats[i].cleanLitter()
    }
    feedAllCovariant(cats)
}

fun feedAllCovariant(animals: HerdCovariant<Animal>) {
    for (i in 0..animals.size) {
        animals[i].feed()
    }
}
