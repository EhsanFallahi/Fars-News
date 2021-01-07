package com.ehsanfallahi.farsnews.util

import androidx.room.TypeConverter
import com.ehsanfallahi.farsnews.model.models.Feed
import com.ehsanfallahi.farsnews.model.models.Item
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object Converter {

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<Item>? {
        if (data == null) {
            return emptyList()
        }

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Item::class.java)
        val adapter = moshi.adapter<List<Item>>(type)
        return adapter.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(objects: List<Item>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Item::class.java)
        val adapter = moshi.adapter<List<Item>>(type)
        return adapter.toJson(objects)
    }
}