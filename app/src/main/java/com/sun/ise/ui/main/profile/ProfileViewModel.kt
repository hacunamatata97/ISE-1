package com.sun.ise.ui.main.profile

import androidx.lifecycle.ViewModel;
import com.sun.ise.data.model.User
import com.sun.ise.data.repository.UserRepository

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    fun getCurrentUser(): User = repository.getCurrentUser()
}
