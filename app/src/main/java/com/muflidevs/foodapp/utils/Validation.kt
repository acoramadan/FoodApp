package com.muflidevs.foodapp.utils

import android.util.Patterns

object Validation {
    fun isCorrectEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isCorrectPassword(password: String): Boolean =
        password.length > 8 && Regex.isUppercaseAndSpecialCharacter(password)
}