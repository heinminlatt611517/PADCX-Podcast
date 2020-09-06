package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "download")
data class DownloadPodCastDataVO(
    @PrimaryKey
    val download_id : String,
    val donwload_podcast_title: String,
    val download_podcast_description : String,
    val download_podcast_url: String,
    val download_audio_path : String
)