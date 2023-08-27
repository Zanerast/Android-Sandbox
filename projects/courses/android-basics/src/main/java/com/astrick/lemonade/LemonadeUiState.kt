package com.astrick.lemonade

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.astrick.androidbasicscompose.R

sealed class LemonadeUiState(
    @DrawableRes val imageResId: Int,
    @StringRes val textResId: Int,
    @StringRes val contentDescriptionId: Int
) {
    object LemonTree : LemonadeUiState(
        R.drawable.lemon_tree,
        R.string.lemon_tree,
        R.string.cd_lemon_tree
    )
    
    object Lemon : LemonadeUiState(
        R.drawable.lemon_squeeze,
        R.string.lemon,
        R.string.cd_lemon
    )
    
    object Lemonade : LemonadeUiState(
        R.drawable.lemon_drink,
        R.string.lemonade,
        R.string.cd_lemonade
    )
    
    object EmptyGlass : LemonadeUiState(
        R.drawable.lemon_restart,
        R.string.empty_glass,
        R.string.cd_empty_glass
    )
}
