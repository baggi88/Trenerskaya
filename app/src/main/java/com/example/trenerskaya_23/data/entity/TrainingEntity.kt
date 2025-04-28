package com.example.trenerskaya_23.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "trainings")
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: LocalDate,
    val duration: Int,
    val notes: String?,
    val trainerId: Long,
    val clientId: Long
) 