package com.sun.ise.data

import com.sun.ise.data.model.EventResult
import retrofit2.Call

interface EventDataSource {
    interface Remote {
        fun getAllEvents(): Call<EventResult>

        fun searchEvent(searchText: String): Call<EventResult>
    }
}
