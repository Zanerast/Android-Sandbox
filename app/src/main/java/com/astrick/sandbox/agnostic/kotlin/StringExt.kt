package com.astrick.sandbox.agnostic.kotlin

@Suppress("unused")
object StringExt {

    /**
     * Capitalizes the first character of the string. If the first character is already uppercase
     * or not a letter, the string remains unchanged.
     *
     * @return A new string with the first character capitalized.
     */
    fun String.capitalize(): String {
        return replaceFirstChar { it.uppercaseChar() }
    }

    /**
     * Removes all occurrences of the specified [remove] strings from the original string.
     *
     * @param remove The strings to be removed.
     * @return A new string with the specified strings removed.
     */
    fun String.remove(vararg remove: String): String {
        var result = this
        remove.forEach { str ->
            result = result.replace(str, "")
        }
        return result
    }

    /**
     * Removes all alphabetic characters (both uppercase and lowercase) from the string.
     *
     * @return A new string with all letters removed.
     */
    fun String.removeLetters(): String {
        return this.replace("[a-zA-Z]".toRegex(), "")
    }

    /**
     * Safely converts a [String] to an [Int].
     *
     * @return The integer value of the string, or `0` if the string cannot be converted.
     */
    fun String.toIntOrZero(): Int {
        return try {
            toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

}