package com.astrick.architecture.errorhandling.presentation

import com.astrick.architecture.R
import com.astrick.architecture.errorhandling.data.DataError
import com.astrick.architecture.errorhandling.data.Resource

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )
        DataError.Network.NoInternet ->  UiText.StringResource(
            R.string.no_internet
        )
        DataError.Network.PayloadTooLarge -> UiText.StringResource(
            R.string.file_too_large
        )
        DataError.Network.RequestTimeout -> UiText.StringResource(
            R.string.the_request_timed_out
        )
        DataError.Network.Serialization -> UiText.StringResource(
            R.string.error_serialization
        )
        DataError.Network.ServerError -> UiText.StringResource(
            R.string.server_error
        )
        DataError.Network.TooManyRequests -> UiText.StringResource(
            R.string.youve_hit_your_rate_limit
        )
        is DataError.Network.Unknown -> {
            if (this.message == null) {
                UiText.StringResource(R.string.unknown_error)
            } else {
                UiText.DynamicString(this.message)
            }
            
        }
    }
}

fun Resource.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}
