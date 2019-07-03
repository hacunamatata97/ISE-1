package com.sun.ise.data.model

import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable

data class EventResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val result: ArrayList<Event>
)

@Parcelize
data class EventSuggestion(
    private val id: Int = 0,
    private val name: String
) : SearchSuggestion {
    override fun getBody(): String = name
}
