package com.ehsanfallahi.farsnews.model.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ehsanfallahi.farsnews.model.models.Feed
import com.ehsanfallahi.farsnews.model.models.Item
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_table")
data class Root(
        @PrimaryKey(autoGenerate = true)
        var id:Long=0,
        @SerializedName("status")
        var status:String,
        @SerializedName("feed")
        @Embedded(prefix = "feed_")
        var feed: Feed,
        @SerializedName("items")
        var items:List<Item>
)
