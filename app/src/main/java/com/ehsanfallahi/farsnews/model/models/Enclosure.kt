package com.ehsanfallahi.farsnews.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Enclosure(
        @SerializedName("link")
     var link:String?,

        @SerializedName("type")
     var type:String,

        @SerializedName("length")
     var length:Int
):Parcelable
