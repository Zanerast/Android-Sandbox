package com.astrick.artspace.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.astrick.androidbasicscompose.R

data class ArtWork(
    @DrawableRes val artId: Int,
    val authorDetails: AuthorDetails
) {
    data class AuthorDetails(
        @StringRes val titleId: Int,
        @StringRes val authorId: Int,
        val year: Int
    )
}

val artList = listOf(
    ArtWork(
        artId = R.drawable.sword,
        authorDetails = ArtWork.AuthorDetails(
            titleId = R.string.art_sword_title,
            authorId = R.string.author_zane,
            year = 2017
        )
    ),
    ArtWork(
        artId = R.drawable.rusty,
        authorDetails = ArtWork.AuthorDetails(
            titleId = R.string.art_rusty_title,
            authorId = R.string.author_zane,
            year = 2012
        )
    ),
    ArtWork(
        artId = R.drawable.gold,
        authorDetails = ArtWork.AuthorDetails(
            titleId = R.string.art_gold_title,
            authorId = R.string.author_afton,
            year = 2012
        )
    )
)
