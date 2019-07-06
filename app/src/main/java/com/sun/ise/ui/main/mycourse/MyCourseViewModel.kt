package com.sun.ise.ui.main.mycourse

import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.EnrollEvent
import com.sun.ise.data.remote.GetEnrollEventAsync
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.UserRepository

class MyCourseViewModel(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository
) : ViewModel() {
    fun getEnrollEvents(): List<EnrollEvent> {
        val user = userRepository.getCurrentUser()!!.user
        val getEnrollEventAsync = GetEnrollEventAsync()
        getEnrollEventAsync.execute(eventRepository.getEnrollEvents(user.id))
        val enrollEventResult = getEnrollEventAsync.get()
        return enrollEventResult.result
    }
}
