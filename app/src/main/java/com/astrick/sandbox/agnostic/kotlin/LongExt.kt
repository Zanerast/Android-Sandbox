package com.astrick.sandbox.agnostic.kotlin

import java.text.SimpleDateFormat
import java.util.Locale

@Suppress("unused")
object LongExt {

	/**
	 * Converts a timestamp (in milliseconds) into a human-readable time format, including seconds.
	 *
	 * The time is formatted in a 12-hour clock with seconds and an AM/PM indicator, e.g., "03:45:30 PM".
	 *
	 * @receiver The timestamp in milliseconds since the epoch (e.g., System.currentTimeMillis()).
	 * @return A string representing the time in a readable format with seconds included.
	 */
	fun Long.toReadableTimeWithSeconds(): String {
		val formatter = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
		return formatter.format(this)
	}

	/**
	 * Checks if the Long value represents a time in the future relative to the current system time.
	 *
	 * @return `true` if the value is greater than the current system time, otherwise `false`.
	 */
	fun Long.isInFuture(): Boolean {
		return this > System.currentTimeMillis()
	}

}
