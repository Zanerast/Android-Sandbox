package com.astrick.sandbox.util.android.context

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

@Suppress("unused")
object ContextExtSharing {

	/**
	 * Opens a sharing interface with a given title and message, with the option to direct users to the Google Play Store.
	 *
	 * This function prepares an Intent to share a text message, either via email, messaging apps, or social media platforms.
	 * It also provides an option to open the app in the Google Play Store by including a Play Store Intent in the chooser.
	 *
	 * @param title The title of the sharing dialog.
	 * @param message The text message to be shared.
	 *
	 * @throws android.content.ActivityNotFoundException If no apps are available to handle the share Intent.
	 */
	fun Context.openShare(title: String, message: String) {
		val shareIntent = Intent().apply {
			action = Intent.ACTION_SEND
			type = "text/plain"
			putExtra(Intent.EXTRA_TEXT, message)
		}

		// Prepare the Intent to open the Play Store
		val openPlayStoreIntent = Intent(Intent.ACTION_VIEW).apply {
			data = "market://details?id=$packageName".toUri()
		}

		// Create a chooser Intent that allows users to pick an app for sharing
		val chooserIntent = Intent.createChooser(shareIntent, title).apply {
			putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(openPlayStoreIntent)) // Include the Play Store option
		}

		startActivity(chooserIntent)
	}

}
