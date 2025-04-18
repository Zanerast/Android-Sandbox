package com.astrick.sandbox.agnostic.android.context

import android.content.Context

object ContextExt {

    /**
     * Provides a [ContextPermissionHelper] instance for checking permissions within the [Context].
     *
     * Example:
     * ```kotlin
     * val canPostNotifications = context.permissions.canPostNotifications
     * ```
     */
    val Context.permissions: ContextPermissionHelper
        get() = ContextPermissionHelper.getInstance(this)

    /**
     * Provides a [ContextSystemServiceHelper] instance for accessing common system services within the [Context].
     *
     * Example:
     * ```kotlin
     * val alarmManager = context.systemServices.alarmManager
     * ```
     */
    val Context.systemServices: ContextSystemServiceHelper
        get() = ContextSystemServiceHelper.getInstance(this)

}
