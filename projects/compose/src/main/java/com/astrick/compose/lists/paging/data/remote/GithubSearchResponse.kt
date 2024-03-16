package com.astrick.compose.lists.paging.data.remote

import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<GithubSearchItemModel> = emptyList(),
    val nextPage: Int? = null
)
