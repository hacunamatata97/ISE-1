package com.sun.ise.util

object StringUtils {
    fun checkNotEmpty(vararg text: String): Boolean {
        text.forEach {
            if (it.isBlank()) return false
        }
        return true
    }
}
