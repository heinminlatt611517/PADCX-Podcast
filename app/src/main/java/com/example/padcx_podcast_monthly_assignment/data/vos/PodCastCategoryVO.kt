package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "podCastCategory")
data class PodCastCategoryVO (
    @PrimaryKey
    @SerializedName("id")
    val id : Int  = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("parent_id")
    val parentId : Int = 0
)
