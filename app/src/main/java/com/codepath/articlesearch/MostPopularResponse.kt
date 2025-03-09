package com.codepath.articlesearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MostPopularResponse(
    @SerialName("results") val results: List<Article>?
)
