package com.astrick.sandbox.util.kotlin

import android.net.Uri

@Suppress("unused")
object NullExt {

	/**
	 * Extension property to check if the object is null or represents a null-like value.
	 * Specifically, for strings, it also checks if the value is "null" (case-insensitive).
	 */
	val Any?.isNull: Boolean
		get() = this == null || (this is String && this.equals("null", ignoreCase = true))
				|| (this is Uri && this.toString().equals("null", ignoreCase = true))

}
