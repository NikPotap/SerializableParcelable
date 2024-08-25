package com.example.serializableparcelable

import android.content.Context
import android.widget.Toast

fun testOfData(
    th: Context,
    firstName: String,
    lastName: String,
    address: String,
    phoneNumber: String
): Boolean {
    if (firstName == "" || lastName == "" || address == "" || phoneNumber == "") {
        Toast.makeText(th, th.resources.getString(R.string.error_null), Toast.LENGTH_SHORT).show()
        return false
    }

    if (firstName.any() { c -> c !in 'а'..'я' }) {
        if (firstName.any() { c -> c !in 'a'..'z' }) {
            Toast.makeText(th, th.resources.getString(R.string.error_name), Toast.LENGTH_SHORT)
                .show()
            return false
        }
    }
    if (phoneNumber.substring(0, 2) != "+7") {
        Toast.makeText(th, th.resources.getString(R.string.error_phone_number), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    val num = phoneNumber.drop(1)
    val notNum = phoneNumber.drop(1).toLongOrNull()
    if (phoneNumber.drop(1).toLongOrNull() == null) {
        Toast.makeText(th, th.resources.getString(R.string.error_phone_not_digits), Toast.LENGTH_SHORT)
            .show()
        return false
    }
    if (phoneNumber.length != 12) {
        Toast.makeText(th, th.resources.getString(R.string.error_phone_bad_lenght), Toast.LENGTH_SHORT)
            .show()
        return false
    }

    return true
}