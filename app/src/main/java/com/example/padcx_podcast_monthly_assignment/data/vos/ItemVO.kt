package com.example.padcx_podcast_monthly_assignment.data.vos

import com.google.gson.annotations.SerializedName

data class ItemVO(
    @SerializedName("added_at_ms")
    val addedAtMs: Long,
    @SerializedName("data")
    val data: UpNextPodCastDataVO? = null,
    @SerializedName("id")
    val ItemId: Int,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("type")
    val type: String
)