package com.sun.ise.data.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.sun.ise.R

const val MALE = 1
const val FE_MALE = 2

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("dob")
    val dateOfBirth: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("major_id")
    val majorId: Int
) {
    fun getGender(context: Context): String = when (gender) {
        MALE -> context.resources.getString(R.string.gender_male)
        FE_MALE -> context.resources.getString(R.string.gender_female)
        else -> context.resources.getString(R.string.gender_unknown)
    }
}

data class UserWrapper(
    val user: User,
    val major: Major
)
