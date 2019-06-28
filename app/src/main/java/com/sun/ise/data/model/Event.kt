package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("leader_id")
    val leaderId: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("price")
    val price: Float,
    @SerializedName("max_participants")
    val maxParticipants: Int,
    @SerializedName("joined_participants")
    val joinedParticipants: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("semester")
    val semester: String,
    @SerializedName("partner_id")
    val partnerId: Int
)

data class EventResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val result: ArrayList<Event>
)
