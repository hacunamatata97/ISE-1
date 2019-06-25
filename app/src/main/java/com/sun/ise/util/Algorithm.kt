package com.sun.ise.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Algorithm {

    fun md5(text: String): String {
        try {
            val algorithm = MessageDigest.getInstance("MD5")
            val messageDigest = algorithm.digest(text.toByteArray())
            val no = BigInteger(1, messageDigest)
            var hashText = no.toString(16)
            while (hashText.length < 32) {
                hashText = "0$hashText"
            }
            return hashText
        } catch (e: NoSuchAlgorithmException) {
            throw NoSuchAlgorithmException()
        }
    }
}
