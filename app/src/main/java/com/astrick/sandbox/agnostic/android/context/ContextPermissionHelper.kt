package com.astrick.sandbox.agnostic.android.context

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.astrick.sandbox.agnostic.android.context.ContextExt.systemServices
import java.lang.ref.WeakReference


/**
 * Provides a singleton helper for accessing commonly used system permissions via syntactic sugar.
 * Internally uses the application context to avoid memory leaks.
 */
class ContextPermissionHelper private constructor(private val context: Context) {

    /**
     * Checks if the app has permission to post notifications.
     *
     * @return `true` if the permission is granted or not required, `false` otherwise.
     */
    val canPostNotifications: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    /**
     * Checks if the app has permission to schedule exact alarms.
     *
     * - For devices running Android S (API 31) or higher, it checks if the app can schedule exact alarms using `AlarmManager.canScheduleExactAlarms()`.
     * - For older versions of Android, it always returns `true`, as the permission is not required.
     *
     * @return `true` if the app has permission to schedule exact alarms, `false` otherwise.
     */
    val canScheduleExactAlarms: Boolean
        get() {
            val alarmManager = context.systemServices.alarmManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                alarmManager.canScheduleExactAlarms()
            } else {
                true
            }
        }

    /**
     * Intent used to request the "Schedule exact alarms" permission from the user.
     *
     * Opens the system settings screen where the user can grant permission.
     *
     * Example usage:
     * `context.startActivity(requestScheduleExactAlarmPermissionIntent)`
     */
    val requestScheduleExactAlarmPermissionIntent: Intent
        @RequiresApi(Build.VERSION_CODES.S)
        get() = Intent(
            Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
            "package:${context.packageName}".toUri()
        )

    /**
     * Check if the overlay permission is granted.
     *
     * @return true if the overlay permission is granted, false otherwise.
     */
    val canOverlay: Boolean
        get() = Settings.canDrawOverlays(context)

    /**
     * Intent used to request the "Appear on top" (overlay) permission from the user.
     *
     * Opens the system settings screen where the user can grant overlay permission
     * for the current app. This permission is required to display content over other apps,
     * such as alarms or floating windows.
     *
     * Example usage:
     * `context.startActivity(overlayIntent)`
     */
    val requestOverlayPermissionIntent: Intent
        get() = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            "package:${context.packageName}".toUri()
        )



    companion object {
        @Volatile
        private var instance: WeakReference<ContextPermissionHelper>? = null

        fun getInstance(context: Context): ContextPermissionHelper {
            return instance?.get() ?: synchronized(this) {
                instance?.get() ?: ContextPermissionHelper(context.applicationContext).also {
                    instance = WeakReference(it)
                }
            }
        }
    }

}
