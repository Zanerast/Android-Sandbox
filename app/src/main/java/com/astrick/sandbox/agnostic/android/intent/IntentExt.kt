package com.astrick.sandbox.agnostic.android.intent

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable

@Suppress("unused")
object IntentExt {

    /**
     * Retrieves a parcelable extra from the [Intent].
     *
     * @param key The key associated with the parcelable data.
     * @return The parcelable object of type [T] or `null` if not found.
     */
    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    /**
     * Retrieves a list of parcelable extras from the [Intent].
     *
     * @param key The key associated with the parcelable list.
     * @return An [ArrayList] of parcelables of type [T] or `null` if not found.
     */
    inline fun <reified T : Parcelable> Intent.parcelableList(key: String): ArrayList<T>? = when {
        SDK_INT >= 33 -> getParcelableArrayListExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
    }

}
