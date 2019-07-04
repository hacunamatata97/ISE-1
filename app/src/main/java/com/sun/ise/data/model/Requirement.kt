package com.sun.ise.data.model

import com.google.gson.annotations.SerializedName

data class Requirement(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("deadline")
    val deadline: String
)

data class RequirementResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("result")
    val requirements: List<Requirement>
)
