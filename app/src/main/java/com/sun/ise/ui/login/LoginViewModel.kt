package com.sun.ise.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun login(email: String, password: String): String {
        var code = "500"
        repository.login(email, password).enqueue(
            object : Callback<LoginResult> {
                override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                    val result = response.body()
                    code = result!!.code
                    repository.saveToken(result.token)
                    val user = result.user
                    repository.saveCurrentUser(user)
                }

                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    code = "500"
                }
            })
        return code
    }
}
