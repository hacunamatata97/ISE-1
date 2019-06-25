package com.sun.ise.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.login.LoginViewModel

object ViewModelUtil {
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = modelClass.cast(f())!!
        }
}
