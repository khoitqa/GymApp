package com.example.gymapp.Model


import com.google.gson.annotations.SerializedName

data class PlanResponse(
    @SerializedName("feed")
    val feed: Feed?
) {
    data class Feed(
        @SerializedName("entry")
        val entry: ArrayList<Entry>?
    ) {
        data class Entry(
            @SerializedName("gsx\$id")
            val gsxid: Gsx?,
            @SerializedName("gsx\$img")
            val gsximg: Gsx?,
            @SerializedName("gsx\$name")
            val gsxname: Gsx?,
            @SerializedName("gsx\$time")
            val gsxtime: Gsx?,
            @SerializedName("gsx\$level")
            val gsxlevel: Gsx?
        )
    }
}