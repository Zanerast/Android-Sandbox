package com.astrick.sandbox.util.android.context

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresPermission

@Suppress("unused")
object ContextExtVibrate {

	/**
	 * Triggers a custom vibration pattern on the device.
	 *
	 * This extension function provides a way to trigger a vibration using the provided [longArray] pattern.
	 * It handles different Android versions:
	 * - For Android 12 (API 31) and above, it uses [VibratorManager] to get the default vibrator.
	 * - For Android 11 (API 30) and below, it falls back to using [Vibrator] (which is deprecated).
	 *
	 * The [longArray] should contain the vibration pattern in milliseconds. Each entry defines the duration of vibration or wait time:
	 * - Positive values represent the duration to vibrate.
	 * - Zero or negative values represent a wait (pause) between vibrations.
	 *
	 * @param longArray The pattern to use for vibration, where each value represents either a vibration duration or wait time in milliseconds.
	 *
	 * @throws SecurityException If the app does not have the `VIBRATE` permission.
	 */
	@RequiresPermission(android.Manifest.permission.VIBRATE)
	fun Context.vibrate(longArray: LongArray) {
		val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
			vibratorManager.defaultVibrator
		} else {
			@Suppress("DEPRECATION")
			getSystemService(VIBRATOR_SERVICE) as Vibrator
		}
		vib.vibrate(VibrationEffect.createWaveform(longArray, -1))
	}

	/**
	 * Stops any ongoing vibration.
	 *
	 * This method handles vibration cancellation for devices running both pre-API level 31
	 * (Android S) and newer. For devices on Android S or above, it utilizes the
	 * `VibratorManager`; for older devices, it uses the deprecated `Vibrator` service.
	 */
	@RequiresPermission(android.Manifest.permission.VIBRATE)
	fun Context.cancelVibration() {
		val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
			vibratorManager.defaultVibrator
		} else {
			@Suppress("DEPRECATION")
			getSystemService(VIBRATOR_SERVICE) as Vibrator
		}
		vibrator.cancel()
	}

}
