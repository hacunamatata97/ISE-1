package com.sun.ise.data.remote

import com.sun.ise.data.UserDataSource
import com.sun.ise.data.api.UserApi
import com.sun.ise.data.model.LoginResult
import retrofit2.Call

class RemoteDataSource(private val userApi: UserApi) : UserDataSource.Remote {

    override fun login(email: String, password: String): Call<LoginResult> =
        userApi.login(email, password)
}
