package com.sun.ise.ui.login

import androidx.lifecycle.ViewModel
import com.sun.ise.data.remote.LoginAsync
import com.sun.ise.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun login(email: String, password: String): String {
        val loginAsync = LoginAsync(repository)
        loginAsync.execute(repository.login(email, password))
        val loginResult = loginAsync.get()
        return loginResult.code
    }
}
