package com.sun.ise.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object StringUtils {

    fun checkNotEmpty(vararg text: String): Boolean {
        text.forEach {
            if (it.isBlank()) return false
        }
        return true
    }

    fun formatPrice(price: Float): String {
        val format = DecimalFormat("#,###")
        return String.format("%s VND", format.format(price))
    }

    fun formatTimeRange(startDate: String, endDate: String, multiLine: Boolean): String =
        if (multiLine) "${simplifyDate(startDate)}\n-\n${simplifyDate(endDate)}"
        else "${simplifyDate(startDate)} - ${simplifyDate(endDate)}"

    fun formatJoinedPeople(joinedParticipants: Int, maxParticipants: Int): String =
        "$joinedParticipants/$maxParticipants"

    fun simplifyDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val formattedDate = inputFormat.parse(date)
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return formatter.format(formattedDate)
    }
}
