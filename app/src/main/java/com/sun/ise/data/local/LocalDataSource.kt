package com.sun.ise.data.local

import android.content.Context
import com.google.gson.Gson
import com.sun.ise.data.UserDataSource
import com.sun.ise.data.model.User
import com.sun.ise.util.SharePrefs

class LocalDataSource private constructor(context: Context) : UserDataSource.Local {

    private val prefs: SharePrefs = SharePrefs(context)

    override fun getToken(): String = prefs.token!!

    override fun saveToken(token: String) {
        prefs.token = token
    }

    override fun getCurrentUser(): User? {
        val userJson = prefs.user
        return if (userJson.isNullOrEmpty()) null
        else Gson().fromJson(userJson, User::class.java)
    }

    override fun saveCurrentUser(user: User) {
        val json = Gson().toJson(user)
        prefs.user = json
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        @JvmStatic
        fun getInstance(context: Context): LocalDataSource {
            return INSTANCE ?: synchronized(LocalDataSource::class.java) {
                val instance = LocalDataSource(context)
                INSTANCE = instance
                instance
            }
        }
    }

}
