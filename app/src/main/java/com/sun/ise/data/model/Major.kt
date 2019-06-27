package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

data class Major(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("acronym")
    val acronym: String
)
