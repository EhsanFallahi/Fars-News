package com.ehsanfallahi.farsnews.model.models

import android.os.Parcelable
import androidx.room.Entity
import com.ehsanfallahi.farsnews.model.models.Enclosure
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(

        @SerializedName("title")
    var title:String,

        @SerializedName("pubDate")
    var pubDate:String,

        @SerializedName("link")
    var link:String,

        @SerializedName("guid")
    var guid:String,

        @SerializedName("author")
    var author:String,

        @SerializedName("thumbnail")
    var thumbnail:String,

        @SerializedName("description")
    var description:String,

        @SerializedName("content")
    var content:String,

        @SerializedName("enclosure")
    var enclosure: Enclosure,

        @SerializedName("categories")
    var categories:List<String>,

        ):Parcelable
