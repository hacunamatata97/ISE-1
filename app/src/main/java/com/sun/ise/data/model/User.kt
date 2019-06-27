package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

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
)

data class UserWrapper(
    val user: User,
    val major: Major
)
