package com.example.trenerskaya_23.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.TypeConverters
import com.example.trenerskaya_23.data.converter.Converters

@Entity(
    tableName = "clients",
    foreignKeys = [
        ForeignKey(
            entity = TrainerEntity::class,
            parentColumns = ["id"],
            childColumns = ["trainerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("trainerId")
    ]
)
@TypeConverters(Converters::class)
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trainerId: Long,
    val name: String,
    val email: String,
    val phone: String?,
    val photoUrl: String?,
    val height: Double?,
    val weight: Double?,
    val goals: List<String>?,
    val notes: String?
) 