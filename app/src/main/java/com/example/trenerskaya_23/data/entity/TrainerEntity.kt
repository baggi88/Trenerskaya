package com.example.trenerskaya_23.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainers")
data class TrainerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val phone: String?,
    val photoUrl: String?,
    val notes: String?
) 