package com.sun.ise.data.remote

import com.sun.ise.data.EventDataSource
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventResult
import retrofit2.Call

class EventRemoteDataSource(private val iseService: IseService) : EventDataSource.Remote {
    override fun getAllEvents(accessToken: String): Call<EventResult> =
        iseService.getAllEvents(accessToken)
}
