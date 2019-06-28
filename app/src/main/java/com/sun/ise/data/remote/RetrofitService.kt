package com.sun.ise.data.remote

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://ise-server.herokuapp.com/"
const val READ_TIMEOUT: Long = 60
const val CONNECTION_TIMEOUT: Long = 60

class RetrofitService private constructor(context: Context) {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AccessTokenInterceptor(context))
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun getService(): IseService = retrofit.create(IseService::class.java)

    companion object {
        private var INSTANCE: RetrofitService? = null

        fun getInstance(context: Context): RetrofitService {
            return INSTANCE ?: synchronized(this) {
                val instance = RetrofitService(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
