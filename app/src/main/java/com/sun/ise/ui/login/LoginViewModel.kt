package com.sun.ise.ui.login

import androidx.lifecycle.ViewModel
import com.sun.ise.data.remote.LoginAsync
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.common.LoginCallback
import com.sun.ise.util.Constants

class LoginViewModel(
    private val callback: LoginCallback,
    private val userRepository: UserRepository
) :
    ViewModel() {

    fun login(email: String, password: String) {
        val loginAsync = LoginAsync(userRepository)
        loginAsync.execute(userRepository.login(email, password))
        val loginResult = loginAsync.get()
        when (loginResult!!.code) {
            Constants.CODE_OK -> {
                callback.onSuccess()
            }
            Constants.CODE_NOT_FOUND -> {
                callback.onInvalidEmailOrPassword()
            }
            Constants.CODE_SERVER_ERROR -> {
                callback.onError(Exception())
            }
        }
    }
}
