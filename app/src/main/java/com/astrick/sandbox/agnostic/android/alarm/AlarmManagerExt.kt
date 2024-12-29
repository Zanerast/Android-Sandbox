package com.astrick.sandbox.agnostic.android.alarm

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build
import androidx.annotation.RequiresPermission

@Suppress("unused")
object AlarmManagerExt {

	/**
	 * Extension function to safely schedule exact alarms with the AlarmManager.
	 *
	 * This function handles the conditional check for devices running Android S (API 31) and above,
	 * ensuring the app has the necessary permission to schedule exact alarms.
	 *
	 * @param triggerAtMillis The time in milliseconds at which the alarm should trigger.
	 * @param pendingIntent The PendingIntent to be executed when the alarm triggers.
	 * @throws IllegalStateException If the app cannot schedule exact alarms on devices running Android S or above.
	 *
	 * @see android.app.AlarmManager.setExactAndAllowWhileIdle
	 */
	@RequiresPermission(value = Manifest.permission.SCHEDULE_EXACT_ALARM, conditional = true)
	fun AlarmManager.scheduleExactAlarm(
		triggerAtMillis: Long,
		pendingIntent: PendingIntent
	) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			if (canScheduleExactAlarms()) {
				setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
			} else {
				throw IllegalStateException("AlarmManager cannot schedule exact alarms")
			}
		} else {
			setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
		}
	}
}
