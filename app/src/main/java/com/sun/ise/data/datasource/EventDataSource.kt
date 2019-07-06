package com.sun.ise.data.datasource

import com.sun.ise.data.model.EnrollEventResult
import com.sun.ise.data.model.EventResult
import retrofit2.Call

interface EventDataSource {
    interface Remote {
        fun getAllEvents(): Call<EventResult>

        fun searchEvent(searchText: String): Call<EventResult>

        fun getEventById(eventId: Int): Call<EventResult>

        fun getEnrollEvents(userId: Int): Call<EnrollEventResult>
    }
}
