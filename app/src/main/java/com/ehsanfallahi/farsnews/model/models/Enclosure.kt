package com.ehsanfallahi.farsnews.model.models

import com.google.gson.annotations.SerializedName

data class Enclosure(
        @SerializedName("link")
     var link:String,

        @SerializedName("type")
     var type:String,

        @SerializedName("length")
     var length:Int
)
