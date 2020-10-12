package com.example.padcx_podcast_monthly_assignment.data.vos

import com.google.gson.annotations.SerializedName

data class PodcastVO(
    @SerializedName("id")
    var podcast_id: String = "",
    @SerializedName("image")
    var podcast_image: String = "",
    @SerializedName("listennotes_url")
    var podcast_listennotes_url: String = "",
    @SerializedName("publisher")
    var podcast_publisher: String = "",
    @SerializedName("thumbnail")
    var podcast_thumbnail: String = "",
    @SerializedName("title")
    var podcast_title: String = ""
)