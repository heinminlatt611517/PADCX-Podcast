package com.example.padcx_podcast_monthly_assignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "podCastCategory")
@IgnoreExtraProperties
data class PodCastCategoryVO (
   @PrimaryKey
    @SerializedName("id")
   var id : Int  = 0,
    @SerializedName("name")
    var name : String = "",
    @SerializedName("parent_id")
   var parent_id : Int = 0,
    @SerializedName("image_url")
    val image_url : String = ""
)
