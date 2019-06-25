package com.sun.ise.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://ise-server.herokuapp.com/"

object RetrofitService {
    private var retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): IseService = retrofit.create(IseService::class.java)
}
