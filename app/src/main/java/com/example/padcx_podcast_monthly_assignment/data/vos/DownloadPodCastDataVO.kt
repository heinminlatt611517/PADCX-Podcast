package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "download")
data class DownloadPodCastDataVO(
    @PrimaryKey
    var DownloadId: String = "",
    var DownloadAudio: String = "",
    var DownloadAudioLengthSecs: Int = 0,
    var DownloadDescription: String = "",
    var DownloadExplicitContent: Boolean = false,
    var DownloadImage: String = "",
    var DownloadLink: String? = "",
    var DownloadListennotesEditUrl: String = "",
    var DownloadListennotesUrl: String = "",
    var DownloadMaybeAudioInvarid: Boolean = false,
    var DownloadPubDateMs: Long = 0,
    var DownloadThumbnail: String = "",
    var DownloadTitle: String = ""
)