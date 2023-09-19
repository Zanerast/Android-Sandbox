package com.astrick.unit4_navigation.lunch_tray.ui.navigation

import androidx.annotation.StringRes
import com.astrick.androidbasicscompose.R

enum class LunchTrayNavDestination(@StringRes val titleId: Int) {
    START(R.string.lunchtray_app_name),
    ENTREE_MENU(R.string.choose_entree),
    SIDE_DISH_MENU(R.string.choose_side_dish),
    ACCOMPANIMENT_MENU(R.string.choose_accompaniment),
    CHECKOUT(R.string.order_checkout)
}
