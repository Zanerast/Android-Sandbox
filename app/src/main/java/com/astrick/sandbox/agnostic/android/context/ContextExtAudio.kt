package com.astrick.sandbox.agnostic.android.context

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri

@Suppress("unused")
object ContextExtAudio {

	/**
	 * Retrieves the title of a ringtone from a given URI.
	 *
	 * @receiver Context used to access the ringtone resources.
	 * @param uri The URI of the ringtone to retrieve.
	 * @return The title of the ringtone as a [String].
	 */
	fun Context.getRingtoneTitleFromUri(uri: Uri): String? {
		val title = RingtoneManager.getRingtone(this, uri)
			.getTitle(this)

		if (title == "null")
			return null

		return title
	}

	/**
	 * Plays the specified audio URI as an alarm sound with adjusted volume.
	 *
	 * - Adjusts the volume of the alarm stream based on the specified `maxVolumeAdjustor`.
	 * - Uses the [AudioAttributes.USAGE_ALARM] to ensure the sound is played as an alarm.
	 *
	 * @param uri The URI of the audio file to play.
	 * @param maxVolumeAdjustor A multiplier (default is `0.5`) to adjust the alarm stream's volume.
	 * For example, `0.5` sets the volume to 50% of the maximum.
	 * @throws SecurityException if the volume change triggers a Do Not Disturb change
	 *   and the caller is not granted notification policy access.
	 */
	fun Context.getRingtoneFrom(
		uri: Uri,
		maxVolumeAdjustor: Double = 0.5
	): Ringtone {
		val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
		val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
		val newVolume: Int = (maxVolume * maxVolumeAdjustor).toInt()
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM, newVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)

		val builder = AudioAttributes.Builder()
		builder.setUsage(AudioAttributes.USAGE_ALARM)

		val ringtone = RingtoneManager.getRingtone(this, Uri.parse(uri.toString()))
		ringtone.audioAttributes = builder.build()
		return ringtone
	}

}
