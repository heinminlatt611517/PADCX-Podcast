package com.example.padcx_podcast_monthly_assignment.data.vos

data class UpNextPodCastDataVO(
    val audio: String,
    val audio_length_sec: Int,
    val description: String,
    val explicit_content: Boolean,
    val id: String,
    val image: String,
    val link: Any,
    val listennotes_edit_url: String,
    val listennotes_url: String,
    val maybe_audio_invalid: Boolean,
    val podcast: PodcastVO,
    val pub_date_ms: Long,
    val thumbnail: String,
    val title: String
)