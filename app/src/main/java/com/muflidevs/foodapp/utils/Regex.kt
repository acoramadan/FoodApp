package com.muflidevs.foodapp.utils

object Regex {
    private val UPPERCASEANDSPECIALCHARACTERREGEX = Regex("^(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).+$")
    fun isUppercaseAndSpecialCharacter(value: String) : Boolean = UPPERCASEANDSPECIALCHARACTERREGEX.matches(value)
}