package com.astrick.sandbox.agnostic.android.context

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

@Suppress("unused")
object ContextExtPermissions {

	/*
	 Alarm Permission
	 */
	/**
	 * Checks if the app has permission to schedule exact alarms.
	 *
	 * - For devices running Android S (API 31) or higher, it checks if the app can schedule exact alarms using `AlarmManager.canScheduleExactAlarms()`.
	 * - For older versions of Android, it always returns `true`, as the permission is not required.
	 *
	 * @return `true` if the app has permission to schedule exact alarms, `false` otherwise.
	 */
	val Context.isPermissionToScheduleExactAlarmsGranted: Boolean
		get() {
			val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
			return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
				alarmManager.canScheduleExactAlarms()
			} else {
				true
			}
		}

	/**
	 * Requests permission to schedule exact alarms on Android S (API 31) or higher by directing
	 * the user to the correct systems screen.
	 */
	@RequiresApi(Build.VERSION_CODES.S)
	fun Context.requestScheduleExactAlarmPermission() {
		val intent = Intent(
			Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
			Uri.parse("package:${this.packageName}")
		)
		this.startActivity(intent)
	}

	/*
	 Notification Permission
	 */
	/**
	 * Checks if the app has permission to post notifications.
	 *
	 * - On devices running Android TIRAMISU (API 33) or above, this checks if the app has the `POST_NOTIFICATIONS` permission.
	 * - On older devices, it always returns `true` as the permission is granted by default.
	 *
	 * @return `true` if the app has permission to post notifications, `false` otherwise.
	 */
	val Context.isPermissionToPostNotificationsGranted: Boolean
		get() {
			return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
			} else {
				true
			}
		}

	/*
	 Overlay Permission
	 */
	/**
	 * Check if the overlay permission is granted.
	 *
	 * @return true if the overlay permission is granted, false otherwise.
	 */
	val Context.isPermissionToOverlayGranted: Boolean
		get() = Settings.canDrawOverlays(this)

	/**
	 * Request the overlay permission.
	 */
	fun Context.requestOverlayPermission() {
		val intent = Intent(
			Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
			Uri.parse("package:${packageName}")
		)
		this.startActivity(intent)
	}
}
