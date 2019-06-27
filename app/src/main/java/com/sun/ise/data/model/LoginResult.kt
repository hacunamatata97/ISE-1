package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

class LoginResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)
