package com.sun.ise.data.local

import android.content.Context
import com.google.gson.Gson
import com.sun.ise.data.UserDataSource
import com.sun.ise.data.model.UserWrapper
import com.sun.ise.util.SharePrefs

class LocalDataSource private constructor(context: Context) : UserDataSource.Local {

    private val prefs: SharePrefs = SharePrefs(context)

    override fun getToken(): String = prefs.token!!

    override fun saveToken(token: String) {
        prefs.token = token
    }

    override fun getCurrentUser(): UserWrapper? {
        val userJson = prefs.user
        return if (userJson.isNullOrEmpty()) null
        else Gson().fromJson(userJson, UserWrapper::class.java)
    }

    override fun saveCurrentUser(userWrapper: UserWrapper) {
        val json = Gson().toJson(userWrapper)
        prefs.user = json
    }

    override fun logout() = prefs.clear()

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
