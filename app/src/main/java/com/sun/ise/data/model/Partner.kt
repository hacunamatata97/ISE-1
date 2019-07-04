package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

data class Partner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("signed")
    val signed: Int
)

data class PartnerResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val partner: Partner
)
