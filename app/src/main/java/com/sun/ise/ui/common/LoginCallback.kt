package com.sun.ise.ui.common

interface LoginCallback {

    fun onSuccess()

    fun onInvalidEmailOrPassword()

    fun onError(exception: Exception)
}
