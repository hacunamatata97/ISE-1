package com.sun.ise.data.repository

import com.sun.ise.data.datasource.EventDataSource
import com.sun.ise.data.model.EventResult
import retrofit2.Call

class EventRepository(
    private val remoteDataSource: EventDataSource.Remote
) : EventDataSource.Remote {

    override fun getAllEvents(): Call<EventResult> =
        remoteDataSource.getAllEvents()

    override fun searchEvent(searchText: String): Call<EventResult> =
        remoteDataSource.searchEvent(searchText)

    override fun getEventById(eventId: Int): Call<EventResult> =
        remoteDataSource.getEventById(eventId)
}
