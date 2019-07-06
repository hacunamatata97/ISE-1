package com.sun.ise.ui.detail

import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.Event
import com.sun.ise.data.model.Major
import com.sun.ise.data.model.Partner
import com.sun.ise.data.model.User
import com.sun.ise.data.remote.GetEventsAsync
import com.sun.ise.data.remote.GetMajorAsync
import com.sun.ise.data.remote.GetPartnerAsync
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.MajorRepository
import com.sun.ise.data.repository.PartnerRepository
import com.sun.ise.data.repository.UserRepository

class DetailViewModel(
    private val majorRepository: MajorRepository,
    private val eventRepository: EventRepository,
    private val partnerRepository: PartnerRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getMajorByEvent(eventId: Int): Major {
        val getMajorAsync = GetMajorAsync()
        getMajorAsync.execute(majorRepository.getMajorByEvent(eventId))
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

    fun getCurrentUser(): User? {
        val userWrapper = userRepository.getCurrentUser()
        return userWrapper?.user
    }
}
