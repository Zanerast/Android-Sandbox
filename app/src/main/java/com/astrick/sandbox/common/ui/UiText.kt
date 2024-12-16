package com.astrick.sandbox.common.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Represents a text that can be either a dynamic string or a string resource with optional arguments.
 */
sealed interface UiText {

    /**
     * A dynamic string that directly holds a text value.
     *
     * @property value The string value.
     */
    data class DynamicString(val value: String) : UiText

    /**
     * A string resource that references a string defined in resources, optionally with format arguments.
     *
     * @property id The resource ID of the string.
     * @property args The arguments to format the string with, if any.
     */
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText

    /**
     * Resolves the [UiText] to a string in a composable context.
     *
     * @return The resolved string value.
     */
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> LocalContext.current.getString(id, *args)
        }
    }

    /**
     * Resolves the [UiText] to a string in a non-composable context.
     *
     * @param context The context used to resolve string resources.
     * @return The resolved string value.
     */
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }

}
