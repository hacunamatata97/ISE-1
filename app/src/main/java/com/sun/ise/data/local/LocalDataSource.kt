package com.sun.ise.data.local

import android.content.Context
import com.sun.ise.data.UserDataSource
import com.sun.ise.util.SharePrefs

class LocalDataSource(context: Context) : UserDataSource.Local {

    private var prefs: SharePrefs? = null

    init {
        prefs = SharePrefs(context)
    }

    override fun saveToken(token: String) {
        prefs?.token = token
    }
}
