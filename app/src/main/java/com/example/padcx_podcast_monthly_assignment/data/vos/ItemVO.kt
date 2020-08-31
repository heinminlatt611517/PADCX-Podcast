package com.example.padcx_podcast_monthly_assignment.data.vos

data class ItemVO(
    val added_at_ms: Long,
    val `data`: UpNextPodCastDataVO,
    val id: Int,
    val notes: String,
    val type: String
)