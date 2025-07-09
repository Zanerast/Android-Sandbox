package com.astrick.sandbox.util.android.context

import android.app.AlarmManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import java.lang.ref.WeakReference

/**
 * Provides a singleton helper for accessing commonly used system services via syntactic sugar.
 * Internally uses the application context to avoid memory leaks.
 */
class ContextSystemServiceHelper private constructor(private val context: Context) {

    /**
     * Lazily accesses the [AlarmManager] system service
     */
    val alarmManager: AlarmManager by lazy { context.getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    /**
     * Lazily accesses the [NotificationManagerCompat] system service
     */
    val notificationManager: NotificationManagerCompat by lazy {  NotificationManagerCompat.from(context) }

    companion object {
        @Volatile
        private var instance: WeakReference<ContextSystemServiceHelper>? = null

        /**
         * Returns a singleton instance of [ContextSystemServiceHelper] scoped to the provided [context].
         */
        fun getInstance(context: Context): ContextSystemServiceHelper {
            return instance?.get() ?: synchronized(this) {
                instance?.get() ?: ContextSystemServiceHelper(context.applicationContext).also {
                    instance = WeakReference(it)
                }
            }
        }
    }

}
