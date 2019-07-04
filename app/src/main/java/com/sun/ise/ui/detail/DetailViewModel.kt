package com.sun.ise.ui.detail

import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.Major
import com.sun.ise.data.model.Partner
import com.sun.ise.data.remote.GetEventsAsync
import com.sun.ise.data.remote.GetMajorAsync
import com.sun.ise.data.remote.GetPartnerAsync
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.PartnerRepository
import com.sun.ise.data.repository.UserRepository

class DetailViewModel(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository,
    private val partnerRepository: PartnerRepository
) : ViewModel() {

    fun getMajorById(majorId: Int): Major {
        val getMajorAsync = GetMajorAsync()
        getMajorAsync.execute(userRepository.getMajorById(majorId))
        val majorResult = getMajorAsync.get()
        return majorResult.major
    }

    fun getEventById(eventId: Int): Event {
        val getEventAsync = GetEventsAsync()
        getEventAsync.execute(eventRepository.getEventById(eventId))
        val eventResult = getEventAsync.get()
        return eventResult.result[0]
    }

    fun getPartnerById(partnerId: Int): Partner {
        val getPartnerAsync = GetPartnerAsync()
        getPartnerAsync.execute(partnerRepository.getPartnerById(partnerId))
        val partnerResult = getPartnerAsync.get()
        return partnerResult.partner
    }
}
