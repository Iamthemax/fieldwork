package com.sipl.aamdarparivar.utils

import java.util.regex.Pattern
object MyValidator {
    public fun isValidName(name: String): Boolean {
        var result = name !== null && name.isNotEmpty() && name.isNotBlank() && name.length > 1
        return result
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        val pattern = Pattern.compile(android.util.Patterns.EMAIL_ADDRESS.toString())
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
    public fun isValidMobileNumber(mobileNumber: String): Boolean {
        val regex = "^[6-9]\\d{9}$"
        return mobileNumber.matches(Regex(regex))
    }
    public fun isValidInputField(name: String): Boolean {
        var result = name !== null && name.isNotEmpty() && name.isNotBlank() && name.length > 1
        return result
    }
    public fun isValidOptionalInputField(name: String): Boolean {
        var result = name !== null && name.isNotEmpty() && name.isNotBlank() && name.length > 1
        return result
    }
    public fun isValidPassword(name: String): Boolean {
        var result = name !== null && name.isNotEmpty() && name.isNotBlank() && name.length > 7
        return result
    }
}
