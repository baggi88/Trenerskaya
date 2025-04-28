package com.example.trenerskaya_23.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.data.entity.TrainingEntity

data class TrainingWithExercises(
    @Embedded
    val training: TrainingEntity,
    
    @Relation(
        parentColumn = "id",
        entityColumn = "trainingId"
    )
    val exercises: List<ExerciseEntity>
) 