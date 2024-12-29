package com.astrick.sandbox.agnostic.kotlin

import kotlin.time.Duration

@Suppress("unused")
object DurationExt {

	/**
	 * Checks if the [Duration] is negative or zero.
	 *
	 * @return `true` if the [Duration] is less than or equal to zero, otherwise `false`.
	 */
	fun Duration.isNegativeOrZero() = this <= Duration.ZERO

	/**
	 * Gets the number of days remaining
	 *
	 * For example:
	 * - A duration of 26 hours would return 1.
	 */
	val Duration.remainingDays: Int
		get() = (this.inWholeDays).toInt()

	/**
	 * Gets the number of hours remaining after accounting for complete days in the duration.
	 *
	 * For example:
	 * - A duration of 26 hours would return 2.
	 * - A duration of 48 hours would return 0.
	 */
	val Duration.remainingHours: Int
		get() = (this.inWholeHours % 24).toInt()

	/**
	 * Gets the number of minutes remaining after accounting for complete hours in the duration.
	 *
	 * For example:
	 * - A duration of 1 hour and 45 minutes would return 45.
	 * - A duration of 2 days, 3 hours, and 15 minutes would return 15.
	 */
	val Duration.remainingMinutes: Int
		get() = (this.inWholeMinutes % 60).toInt()

	/**
	 * Gets the number of seconds remaining after accounting for complete minutes in the duration.
	 */
	val Duration.remainingSeconds: Int
		get() = (this.inWholeSeconds % 60).toInt()

}
