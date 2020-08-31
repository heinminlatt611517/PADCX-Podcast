package com.example.padcx_podcast_monthly_assignment.data.vos

import com.google.gson.annotations.SerializedName

data class PodCastCategoryVO (
    @SerializedName("id")
    val id : Int  = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("parent_id")
    val parentId : Int = 0
)
