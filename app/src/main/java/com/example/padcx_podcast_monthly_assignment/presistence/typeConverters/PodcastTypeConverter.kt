package com.example.padcx_podcast_monthly_assignment.presistence.typeConverters

import androidx.room.TypeConverter
import com.example.padcx_podcast_monthly_assignment.data.vos.ItemVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodcastVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PodcastTypeConverter {
    @TypeConverter
    fun toString(dataList: PodcastVO):String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr:String): PodcastVO {
        val dataListType = object : TypeToken<PodcastVO>(){}.type
        return Gson().fromJson(ListJsonStr,dataListType)
    }
}