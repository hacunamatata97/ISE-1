package com.sun.ise.ui.detail

import androidx.lifecycle.ViewModel
import com.sun.ise.data.repository.EventRepository
import com.sun.ise.data.repository.UserRepository

class DetailViewModel(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

}
