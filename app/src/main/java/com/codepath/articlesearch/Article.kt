package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchNewsResponse(
    @SerialName("response")
    val response: BaseResponse?
)
@Keep
@Serializable
data class MostPopularNewsResponse(
    @SerialName("results")
    val results: List<MostPopularArticle>? = emptyList()
)

@Keep
@Serializable
data class MostPopularArticle(
    @SerialName("url")
    val webUrl: String?,
    @SerialName("published_date")
    val pubDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("abstract")
    val abstract: String?,
    @SerialName("byline")
    val byline: String?,
    @SerialName("media")
    val media: List<Media>?
) : java.io.Serializable {
    val mediaImageUrl: String?
        get() = media?.firstOrNull()?.mediaMetadata?.lastOrNull()?.url
}

@Keep
@Serializable
data class Media(
    @SerialName("media-metadata")
    val mediaMetadata: List<MediaMetadata>?
) : java.io.Serializable

@Keep
@Serializable
data class MediaMetadata(
    @SerialName("url")
    val url: String?
) : java.io.Serializable

@Keep
@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<Article>?
)

@Keep
@Serializable
data class Article(
    @SerialName("web_url")
    val webUrl: String?,
    @SerialName("pub_date")
    val pubDate: String?,
    @SerialName("headline")
    val headline: HeadLine?,
    @SerialName("multimedia")
    val multimedia: List<MultiMedia>?,
    @SerialName("abstract")
    val abstract: String?,
    @SerialName("byline")
    val byline: Byline?,
) : java.io.Serializable {
    val mediaImageUrl =
        "https://www.nytimes.com/${multimedia?.firstOrNull { it.url != null }?.url ?: ""}"
}

@Keep
@Serializable
data class HeadLine(
    @SerialName("main")
    val main: String
) : java.io.Serializable

@Keep
@Serializable
data class Byline(
    @SerialName("original")
    val original: String? = null
) : java.io.Serializable

@Keep
@Serializable
data class MultiMedia(
    @SerialName("url")
    val url: String?
) : java.io.Serializable