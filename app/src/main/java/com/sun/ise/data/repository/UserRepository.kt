package com.sun.ise.data.repository

import com.sun.ise.data.UserDataSource
import com.sun.ise.data.model.LoginResult
import retrofit2.Call

class UserRepository(
    private val localDataSource: UserDataSource.Local,
    private val remoteDataSource: UserDataSource.Remote
) : UserDataSource.Local, UserDataSource.Remote {

    override fun getToken(): String = localDataSource.getToken()

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun login(email: String, password: String): Call<LoginResult> =
        remoteDataSource.login(email, password)
}
