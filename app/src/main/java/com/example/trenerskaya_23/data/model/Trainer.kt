package com.example.trenerskaya_23.data.model

data class Trainer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val height: Int?,
    val weight: Double?,
    val experience: Int?,
    val achievements: List<String>,
    val photoUrl: String?
) 