package com.astrick.sandbox.kotlin.generics

fun <T: Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0
}
