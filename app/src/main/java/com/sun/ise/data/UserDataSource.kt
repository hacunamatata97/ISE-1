package com.sun.ise.data

import com.sun.ise.data.model.LoginResult
import com.sun.ise.data.model.User
import retrofit2.Call

interface UserDataSource {

    interface Local {
        fun getToken(): String

        fun saveToken(token: String)
    }

    interface LocalUser : Local {
        fun getCurrentUser(): User

        fun saveCurrentUser(user: User)
    }

    interface Remote {
        fun login(email: String, password: String): Call<LoginResult>
    }
}
