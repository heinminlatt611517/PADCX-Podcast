package com.example.padcx_podcast_monthly_assignment.data.vos

import com.google.gson.annotations.SerializedName

data class PodcastVO(
    @SerializedName("id")
    val podcastId: String = "",
    @SerializedName("image")
    val podCastImage: String = "",
    @SerializedName("listennotes_url")
    val podCastListennotesUrl: String = "",
    @SerializedName("publisher")
    val podCastPublisher: String = "",
    @SerializedName("thumbnail")
    val podCastThumbnail: String = "",
    @SerializedName("title")
    val podCastTitle: String = ""
)