package com.sun.ise.data

import com.sun.ise.data.model.EventResult
import retrofit2.Call

interface EventDataSource {
    interface Remote {
        fun getAllEvents(accessToken: String): Call<EventResult>
    }
}
