package com.sun.ise.data.model

data class User(
    val id: Int,
    val name: String,
    val gender: Int,
    val dateOfBirth: String,
    val code: String,
    val phone: String,
    val type: String,
    val email: String,
    val token: String,
    val majorId: Int
)
