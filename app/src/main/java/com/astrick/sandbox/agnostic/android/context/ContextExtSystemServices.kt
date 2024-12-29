package com.astrick.sandbox.agnostic.android.context

import android.app.AlarmManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat

@Suppress("unused")
object ContextExtSystemServices {

	/**
	 * Retrieve the [AlarmManager] system service from the [Context].
	 *
	 * Simplifies accessing the [AlarmManager] system service by casting the system service to the appropriate type.
	 * It is equivalent to calling `context.getSystemService(Context.ALARM_SERVICE)` but with type safety ensured.
	 *
	 * @return The [AlarmManager] system service instance.
	 */
	val Context.alarmManager: AlarmManager
		get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager


	/**
	 * Retrieves the [NotificationManagerCompat] instance for managing notifications.
	 *
	 * Simplifies notification management by providing an easy-to-use access point.
	 *
	 * @return The [NotificationManagerCompat] instance.
	 */
	val Context.notificationManager: NotificationManagerCompat
		get() = NotificationManagerCompat.from(this)

}
