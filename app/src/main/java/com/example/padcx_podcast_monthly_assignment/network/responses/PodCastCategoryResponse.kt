package com.example.padcx_podcast_monthly_assignment.network.responses

import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.google.gson.annotations.SerializedName

data class PodCastCategoryResponse (
    @SerializedName("genres")
    val genres : ArrayList<PodCastCategoryVO> = arrayListOf()
)
