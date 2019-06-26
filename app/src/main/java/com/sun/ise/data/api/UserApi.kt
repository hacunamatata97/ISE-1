package com.sun.ise.data.api

import com.sun.ise.data.model.LoginResult
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @POST("/authenticate/")
    fun login(@Header("email") email: String, @Header("password") password: String): Call<LoginResult>
}
