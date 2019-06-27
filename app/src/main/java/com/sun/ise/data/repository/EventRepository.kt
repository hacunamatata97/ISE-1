package com.sun.ise.data.repository

import com.sun.ise.data.EventDataSource
import com.sun.ise.data.UserDataSource
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.EventResult
import retrofit2.Call

class EventRepository(
    private val localDataSource: UserDataSource.Local,
    private val remoteDataSource: EventDataSource.Remote
) :
    UserDataSource.Local, EventDataSource.Remote {

    override fun getToken(): String = localDataSource.getToken()

    override fun saveToken(token: String) {
        saveToken(token)
    }

    override fun getAllEvents(): Call<EventResult> =
        remoteDataSource.getAllEvents()

    override fun searchEvent(searchText: String): Call<EventResult> =
        remoteDataSource.searchEvent(searchText)
}
