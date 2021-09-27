package com.mayouf.catstechnicaltest.domain.model

import com.google.gson.annotations.SerializedName

data class Cats(

    @SerializedName("breeds") val breeds: List<String>,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)