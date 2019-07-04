package com.sun.ise.data.remote

import com.sun.ise.data.datasource.EventDataSource
import com.sun.ise.data.model.EventResult
import retrofit2.Call

class EventRemoteDataSource(private val iseService: IseService) : EventDataSource.Remote {
    override fun getAllEvents(): Call<EventResult> =
        iseService.getAllEvents()

    override fun searchEvent(searchText: String): Call<EventResult> =
        iseService.searchEvent(searchText)

    override fun getEventById(eventId: Int): Call<EventResult> =
        iseService.getEventById(eventId)
}
