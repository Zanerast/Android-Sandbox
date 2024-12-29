package com.astrick.sandbox.agnostic.android.context

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build

@Suppress("unused")
object ContextExtEmail {

	/**
	 * Sends an email to the developer with the specified subject and phone details.
	 *
	 * @param sendToEmail The email address to send the email to.
	 * @param subject The subject of the email.
	 *
	 * @throws [android.content.ActivityNotFoundException]
	 */
	fun Context.sendEmailToDeveloper(
		sendToEmail: String,
		subject: String
	) {
		val phoneDetails = getPhoneDetails()

		val intent = Intent(Intent.ACTION_SENDTO).apply {
			data = Uri.parse("mailto:")
			putExtra(Intent.EXTRA_EMAIL, listOf(sendToEmail).toTypedArray())
			putExtra(Intent.EXTRA_SUBJECT, subject)
			putExtra(Intent.EXTRA_TEXT, phoneDetails)
		}
		startActivity(intent)
	}

	// Retrieves phone details to include in a support email.
	private fun Context.getPhoneDetails(): String {
		val versionName = try {
			packageManager.getPackageInfo(packageName, 0).versionName
		} catch (e: PackageManager.NameNotFoundException) {
			"Unknown"
		}

		// The empty space represents where the user can type their message
		return """
        
        
        
        -------------------------------
        NOTES FOR THE DEVELOPER:
        
        Brand: ${Build.BRAND}
        Model: ${Build.MODEL}
        Manufacture: ${Build.MANUFACTURER}
        SDK: ${Build.VERSION.SDK_INT}
        App Version: $versionName""".trimIndent()
	}

}
