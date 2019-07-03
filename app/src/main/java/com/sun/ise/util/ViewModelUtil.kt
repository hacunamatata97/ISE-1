package com.sun.ise.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelUtil {
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = modelClass.cast(f())!!
        }
}
