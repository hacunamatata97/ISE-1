package com.sun.ise.data.datasource

import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.MajorResult
import com.sun.ise.data.model.UserWrapper
import retrofit2.Call

interface UserDataSource {

    interface Local {
        fun getToken(): String

        fun saveToken(token: String)

        fun getCurrentUser(): UserWrapper?

        fun saveCurrentUser(userWrapper: UserWrapper)

        fun logout()
    }

    interface Remote {
        fun login(email: String, password: String): Call<LoginResult>

        fun getMajorById(majorId: Int) : Call<MajorResult>
    }
}
