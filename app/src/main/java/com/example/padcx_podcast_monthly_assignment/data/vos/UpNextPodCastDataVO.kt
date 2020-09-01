package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class UpNextPodCastDataVO(
    @SerializedName("id")
    val UpNextId: String = "",
    @SerializedName("audio")
    val UpNextAudio: String = "",
    @SerializedName("audio_length_sec")
    val UpNextAudioLengthSecs: Int = 0,
    @SerializedName("description")
    val UpNextDescription: String = "",
    @SerializedName("explicit_content")
    val UpNextExplicitContent: Boolean = false,
    @SerializedName("image")
    val UpNextImage: String = "",
    @SerializedName("link")
    val UpNextLink: String? = "",
    @SerializedName("listennotes_edit_url")
    val UpNextListennotesEditUrl: String = "",
    @SerializedName("listennotes_url")
    val UpNextListennotesUrl: String = "",
    @SerializedName("maybe_audio_invalid")
    val UpNextMaybeAudioInvalid: Boolean = false,
    @Embedded
    @SerializedName("podcast")
    val UpNextPodcast: PodcastVO? = null,
    @SerializedName("pub_date_ms")
    val UpNextPubDateMs: Long = 0,
    @SerializedName("thumbnail")
    val UpNextThumbnail: String = "",
    @SerializedName("title")
    val UpNextTitle: String = ""
)