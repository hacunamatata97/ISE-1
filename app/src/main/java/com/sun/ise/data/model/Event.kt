package com.sun.ise.data.model

import android.content.Context
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.gson.annotations.SerializedName
import com.sun.ise.R
import kotlinx.android.parcel.Parcelize

const val STATUS_ON_GOING = 1
const val STATUS_FINISHED = 3
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
) : Parcelable {
    fun getStatus(context: Context): String = when(status) {
        STATUS_ON_GOING -> context.getString(R.string.status_on_going)
        STATUS_FINISHED -> context.getString(R.string.status_finished)
        else -> context.getString(R.string.unknown)
    }
}

data class EventResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val result: ArrayList<Event>
)

@Parcelize
data class EventSuggestion(
    val id: Int = 0,
    val name: String
) : SearchSuggestion {
    override fun getBody(): String = name
}
