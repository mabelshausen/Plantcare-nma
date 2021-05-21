package be.howest.marijnabelshausen.plantcare.domain

import java.util.*

data class Plant (
    val id: Int,
    val name: String,
    val sciName: String,
    val age: Int,
    val waterFreq: Int,
    val lastWatered: String
)