package com.sun.ise.data.repository

import com.sun.ise.data.UserDataSource
import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.MajorResult
import com.sun.ise.data.model.UserWrapper
import retrofit2.Call

class UserRepository(
    private val localDataSource: UserDataSource.Local,
    private val remoteDataSource: UserDataSource.Remote
) : UserDataSource.Local, UserDataSource.Remote {

    override fun getToken(): String = localDataSource.getToken()

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun getCurrentUser(): UserWrapper? = localDataSource.getCurrentUser()

    override fun saveCurrentUser(userWrapper: UserWrapper) {
        localDataSource.saveCurrentUser(userWrapper)
    }

    override fun login(email: String, password: String): Call<LoginResult> =
        remoteDataSource.login(email, password)

    override fun logout() = localDataSource.logout()

    override fun getMajorById(majorId: Int): Call<MajorResult> =
        remoteDataSource.getMajorById(majorId)
}
