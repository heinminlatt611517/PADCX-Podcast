package com.example.padcx_podcast_monthly_assignment.presistence.typeConverters

import androidx.room.TypeConverter
import com.example.padcx_podcast_monthly_assignment.data.vos.ItemVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemTypeConverter {
    @TypeConverter
    fun toString(itemList: List<ItemVO>) : String {
        return Gson().toJson(itemList)
    }

    @TypeConverter
    fun toItemList(itemListJsonString: String) : List<ItemVO>{
        val itemListType = object : TypeToken<List<ItemVO>>() {}.type
        return Gson().fromJson(itemListJsonString, itemListType)
    }
}