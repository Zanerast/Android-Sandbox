package com.astrick.core.extensions

fun String.remove(vararg remove: String): String {
    var result = this
    remove.forEach { str ->
        result = result.replace(str, "")
    }
    return result
}

fun String.removeLetters(): String {
    return this.replace("[a-zA-Z]".toRegex(), "")
}

fun String.capitalize(): String {
    return replaceFirstChar { it.uppercaseChar() }
}