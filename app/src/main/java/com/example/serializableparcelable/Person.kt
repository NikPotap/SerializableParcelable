package com.example.serializableparcelable

import java.io.Serializable

class Person(
    val firstName: String,
    val lastName: String,
    val address: String,
    val phoneNumber: String
) : Serializable {
    override fun toString() = "$lastName $firstName."
}