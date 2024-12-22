package com.astrick.sandbox.compose.navigation.bottom.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.astrick.compose.R

/**
 * Represents the screens available in the bottom navigation bar.
 *
 * @property route The unique route identifier for the screen.
 * @property titleId The string resource ID for the screen's title.
 * @property imageId The drawable resource ID for the screen's icon.
 */
sealed class BottomNavScreens(
    val route: String,
    @StringRes val titleId: Int,
    @DrawableRes val imageId: Int
) {
    object Home : BottomNavScreens("home", R.string.bottom_nav_home_title, R.drawable.ic_android)

    object Email : BottomNavScreens("email", R.string.bottom_nav_email_title, R.drawable.ic_email)

    object Phone : BottomNavScreens("phone", R.string.bottom_nav_phone_title, R.drawable.ic_phone)

    companion object {
        val items = listOf(Home, Email, Phone)
    }
}
