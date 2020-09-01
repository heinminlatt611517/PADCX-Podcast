package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.padcx_podcast_monthly_assignment.presistence.typeConverters.ItemTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "upNextPodCast")
@TypeConverters(ItemTypeConverter::class)
data class UpNextPodCastPlaylistsVO(
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image")
    val image: String ="",
    @SerializedName("items")
    val items: List<ItemVO> = listOf(),
    @SerializedName("last_timestamp_ms")
    val lastTimestampMs: Long = 0,
    @SerializedName("listennotes_url")
    val listennotesUrl: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("visibility")
    val visibility: String = ""
)