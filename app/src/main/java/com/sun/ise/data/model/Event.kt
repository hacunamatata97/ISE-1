package com.sun.ise.data.model

import java.sql.Date

data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val leaderId: Int,
    val status: Int,
    val price: Float,
    val maxParticipants: Int,
    val joinedParticipants: Int,
    val startDate: Date,
    val endDate: Date,
    val semester: String,
    val partnerId: Int
)

data class EventResult(
    val code: String,
    val result: ArrayList<Event>
)
