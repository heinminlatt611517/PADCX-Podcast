package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "podCast")
data class PodCastDataVO(
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @SerializedName("audio")
    val audio: String = "",
    @SerializedName("audio_length_sec")
    val audioLengthSecs: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("explicit_content")
    val explicitContent: Boolean = false,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("link")
    val link: String? = "",
    @SerializedName("listennotes_edit_url")
    val listennotesEditUrl: String = "",
    @SerializedName("listennotes_url")
    val listennotesUrl: String = "",
    @SerializedName("maybe_audio_invalid")
    val maybeAudioInvalid: Boolean = false,
    @Embedded
    @SerializedName("podcast")
    val podcast: PodcastVO? = null,
    @SerializedName("pub_date_ms")
    val pubDateMs: Long = 0,
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("title")
    val title: String = ""

)