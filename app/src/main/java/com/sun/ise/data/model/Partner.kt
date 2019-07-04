package com.sun.ise.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable

data class PartnerResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val partner: Partner
)
