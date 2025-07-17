package com.astrick.sandbox.kotlin.versions

import kotlin.reflect.KClass

/**
 * What's new in Kotlin 2.1.0
 * [Reference Docs](https://kotlinlang.org/docs/whatsnew21.html)
 */

/**
 * Guard conditions in when with a subject
 *
 * Feature still in preview: -Xwhen-guards
 * [Reference Docs](https://kotlinlang.org/docs/whatsnew21.html?#guard-conditions-in-when-with-a-subject)
 */
sealed interface Animal {
    data class Cat(val mouseHunter: Boolean) : Animal {
        fun feedCat() {}
    }

    data class Dog(val breed: String) : Animal {
        fun feedDog() {}
    }
}

fun feedAnimal(animal: Animal) {
    when (animal) {
        // Branch with only the primary condition. Calls `feedDog()` when `animal` is `Dog`
        is Animal.Dog -> animal.feedDog()
        // Branch with both primary and guard conditions. Calls `feedCat()` when `animal` is `Cat` and is not `mouseHunter`
        is Animal.Cat if !animal.mouseHunter -> animal.feedCat()
        // Prints "Unknown animal" if none of the above conditions match
        else -> println("Unknown animal")
    }
}

/**
 * Non-local break and continue.
 *
 * Feature still in preview: -Xnon-local-break-continue
 * [Reference Docs](https://kotlinlang.org/docs/whatsnew21.html?#non-local-break-and-continue)
 */
fun processList(elements: List<Int?>): Boolean {
    for (element in elements) {
        val variable = element ?: run {
            println("Element is null or invalid, continuing...")
        }
        if (variable == 0) return true // If variable is zero, return true
    }
    return false
}

// Decompiled Java Code:
// public static final boolean processList(@NotNull List elements) {
//    Intrinsics.checkNotNullParameter(elements, "elements");
//
//    for(Integer element : elements) {
//        if (element != null) {
//            int variable = element;
//            if (variable == 0) {
//                return true;
//            }
//        } else {
//            int var4 = 0;
//            System.out.println("Element is null or invalid, continuing...");
//        }
// }

/**
 * Multi-dollar string interpolation
 *
 * Feature still in preview: -Xmulti-dollar-interpolation
 * [Reference Docs](https://kotlinlang.org/docs/whatsnew21.html?#multi-dollar-string-interpolation)
 */
const val oldStringLiteral = """${'$'}schema"""
const val newStringLiteral= $$"""$schema"""
const val oldString = "\$schema"
const val newString = $$"$schema"

/**
 * Global warning suppression
 *
 * [Reference Docs](https://kotlinlang.org/docs/whatsnew21.html?#global-warning-suppression)
 */
/*
   // build.gradle.kts
   kotlin {
     compilerOptions {
        extraWarnings.set(true)
        freeCompilerArgs.add("-Xsuppress-warning=CAN_BE_VAL")
     }
   }

   // multiple
   kotlin {
        compilerOptions {
            freeCompilerArgs.addAll(
                listOf(
                    "-Xsuppress-warning=NOTHING_TO_INLINE",
                    "-Xsuppress-warning=NO_TAIL_CALLS_FOUND"
                )
            )
        }
   }
*/
