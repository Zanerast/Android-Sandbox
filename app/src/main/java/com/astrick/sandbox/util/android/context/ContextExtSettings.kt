package com.astrick.sandbox.util.android.context

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.net.toUri

@Suppress("unused")
object ContextExtSettings {

	/**
	 * Opens the app settings page for the current application.
	 *
	 * This function launches an intent that opens the application details settings page
	 * where the user can manage app permissions and other settings.
	 */
	fun Context.openSettingsToAppPage() {
		val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
		intent.data = "package:$packageName".toUri()
		startActivity(intent)
	}

}
