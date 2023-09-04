package com.astrick.superhero.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SuperHero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
