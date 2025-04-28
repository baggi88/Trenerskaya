package com.example.trenerskaya_23.data.model

data class Client(
    val id: String,
    val trainerId: String,
    val firstName: String,
    val lastName: String,
    val height: Int?,
    val weight: Double?,
    val goals: List<String>,
    val notes: String,
    val photoUrl: String?
) 