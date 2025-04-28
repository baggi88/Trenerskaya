package com.example.trenerskaya_23.data.model

import java.time.LocalDate

data class Training(
    val id: Long = 0,
    val trainerId: Long,
    val clientId: Long,
    val name: String,
    val date: LocalDate,
    val duration: Int,
    val notes: String?
) 