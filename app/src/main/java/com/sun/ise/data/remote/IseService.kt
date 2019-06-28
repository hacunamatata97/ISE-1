package com.sun.ise.data.remote

import com.sun.ise.data.model.EventResult
import com.sun.ise.data.model.LoginResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IseService {

    @POST("/authenticate/")
    fun login(@Header("email") email: String, @Header("password") password: String): Call<LoginResult>

    @GET("/api/events/")
    fun getAllEvents(): Call<EventResult>

    @GET("/api/events")
    fun searchEvent(@Query("q") name: String): Call<EventResult>
}
