package com.sun.ise.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.Event
import com.sun.ise.data.remote.NetworkCallAsync
import com.sun.ise.data.repository.EventRepository

class HomeViewModel(private val repository: EventRepository) : ViewModel() {

    fun getAllEvents(): LiveData<List<Event>> {
        val networkCallAsync = NetworkCallAsync()
        networkCallAsync.execute(repository.getAllEvents(repository.getToken()))
        val eventResult = networkCallAsync.get()
        val liveData = MutableLiveData<List<Event>>()
        liveData.value = eventResult.result
        return liveData
    }
}
