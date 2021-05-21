package be.howest.marijnabelshausen.plantcare.domain

import java.util.*

data class Plant (
    val id: Int,
    var name: String,
    var sciName: String,
    var age: Int,
    var room_id: Int,
    var waterFreq: Int,
    var lastWatered: String
)