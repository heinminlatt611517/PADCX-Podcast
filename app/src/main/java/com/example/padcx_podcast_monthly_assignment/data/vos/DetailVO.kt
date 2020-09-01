package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class DetailVO(
    val audio: String,
    val audio_length_sec: Int,
    val description: String,
    val explicit_content: Boolean,
    val id: String,
    val image: String,
    val link: String,
    val listennotes_edit_url: String,
    val listennotes_url: String,
    val maybe_audio_invalid: Boolean,
    val podcast: PodcastDetailVO,
    val pub_date_ms: Long,
    val thumbnail: String,
    val title: String
)

