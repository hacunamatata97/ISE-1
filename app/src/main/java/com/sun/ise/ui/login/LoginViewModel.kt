package com.sun.ise.ui.login

import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun login(email: String, password: String): String {
        var code = "500"
        userRepository.login(email, password).enqueue(
            object : Callback<LoginResult> {
                override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                    val result = response.body()
                    code = result!!.code
                    userRepository.saveToken(result.token)
                    val user = result.user
                    userRepository.saveCurrentUser(user)
                }

                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    code = "500"
                }
            })
        return code
    }
}
