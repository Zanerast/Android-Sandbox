package com.astrick.sandbox.agnostic.android.context

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.net.toUri

@Suppress("unused")
object ContextExtAudio {

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

		val ringtone = RingtoneManager.getRingtone(this, uri.toString().toUri())
		ringtone.audioAttributes = builder.build()
		return ringtone
	}

}
