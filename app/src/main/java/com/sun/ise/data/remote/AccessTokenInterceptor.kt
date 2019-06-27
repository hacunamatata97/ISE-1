package com.sun.ise.data.remote

import android.content.Context
import com.sun.ise.util.SharePrefs
import okhttp3.Interceptor
import okhttp3.Response

const val HEADER_ACCESS_TOKEN = "access-token"

class AccessTokenInterceptor(context: Context) : Interceptor {

    private val prefs = SharePrefs(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (request.url().encodedPath().equals("/authenticate/", true)
            && request.method().equals("post", true)
        ) {
            chain.proceed(request)
        } else {
            val newRequest = request.newBuilder()
                .addHeader(HEADER_ACCESS_TOKEN, prefs.token!!)
                .build()
            chain.proceed(newRequest)
        }
    }
}
