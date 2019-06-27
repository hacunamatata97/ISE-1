package com.sun.ise.util

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_FILENAME = "com.sun.ise.prefs"
private const val PREFS_TOKEN = "TOKEN"
private const val PREFS_CURRENT_USER = "CURRENT_USER"

class SharePrefs(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString(PREFS_TOKEN, "")
        set(value) = prefs.edit().putString(PREFS_TOKEN, value).apply()

    var user: String?
        get() = prefs.getString(PREFS_CURRENT_USER, "")
        set(value) = prefs.edit().putString(PREFS_CURRENT_USER, value).apply()
}
