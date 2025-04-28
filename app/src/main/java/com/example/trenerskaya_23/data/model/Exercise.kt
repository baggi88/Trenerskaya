package com.example.trenerskaya_23.data.model

data class Exercise(
    val id: Long = 0,
    val trainingId: Long,
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Float?,
    val notes: String?
) 