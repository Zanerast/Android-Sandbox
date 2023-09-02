package com.astrick.courses.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleResId: Int,
    val numberOfAssociatedCourses: Int,
    @DrawableRes val imageResId: Int,
)
