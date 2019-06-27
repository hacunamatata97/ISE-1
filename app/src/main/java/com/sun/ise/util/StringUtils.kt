package com.sun.ise.util
import java.text.SimpleDateFormat
import java.util.Locale

object StringUtils {

    fun checkNotEmpty(vararg text: String): Boolean {
        text.forEach {
            if (it.isBlank()) return false
        }
        return true
    }

    fun formatTimeRange(startDate: String, endDate: String): String =
        "${simplifyDate(startDate)}\n-\n${simplifyDate(endDate)}"

    fun formatJoinedPeople(joinedParticipants: Int, maxParticipants: Int): String =
        "$joinedParticipants/$maxParticipants"

    fun simplifyDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val formattedDate = inputFormat.parse(date)
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return formatter.format(formattedDate)
    }

    fun getGender(gender: Int): String = when(gender) {
        1 -> "Male"
        2 -> "Female"
        else -> "LGBT"
    }
}
