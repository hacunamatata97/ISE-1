package com.sun.ise.data.remote

import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventResult
import com.sun.ise.data.model.LoginResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IseService {

    @POST("/authenticate/")
    fun login(@Header("email") email: String, @Header("password") password: String): Call<LoginResult>

    @GET("/api/events/")
    fun getAllEvents(@Header("access-token") accessToken: String): Call<EventResult>
}
