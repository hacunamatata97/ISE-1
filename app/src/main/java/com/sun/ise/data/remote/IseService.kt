package com.sun.ise.data.remote

import com.sun.ise.data.model.EventResult
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.MajorResult
import com.sun.ise.data.model.PartnerResult
import retrofit2.Call
import retrofit2.http.*

interface IseService {

    @POST("/authenticate/")
    fun login(@Header("email") email: String, @Header("password") password: String): Call<LoginResult>

    @GET("/api/events/")
    fun getAllEvents(): Call<EventResult>

    @GET("/api/events")
    fun searchEvent(@Query("q") name: String): Call<EventResult>

    @GET("/api/major/{majorId}")
    fun getMajorById(@Path("majorId") majorId: Int): Call<MajorResult>

    @GET("/api/event/{eventId}")
    fun getEventById(@Path("eventId") eventId: Int): Call<EventResult>

    @GET("/api/partner/{partnerId}")
    fun getPartnerById(@Path("partnerId") partnerId: Int): Call<PartnerResult>
}
